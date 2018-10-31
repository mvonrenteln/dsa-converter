package com.github.mvonrenteln.dsa.converter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.apache.velocity.VelocityContext
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


val parameterDescription = """
    |Parameter:
    |  1. Name der Eingabe-Datei oder des Eingabe-Verzeichnisses,
    |     - bei Angabe eines Verzeichnisses werden alle YAML und JSON-Dateien in diesem Verzeichnis gelesen
    |       - die Dateien sollten durchnummeriert werden mit "(1)", "(2)" etc. am Ende des Dateinamens
    |       - Die Daten der Dateien werden zusammengefügt ihrer Nummerierung folgend
    |       - Die Daten der Gruppe (Name, Mitglieder, Titel, Verfasser, Einleitung) werden immer von der LETZTEN Datei übernommen, da davon ausgegangen wird, dass diese die aktuellste ist (in den restlichen können die Werte also einfach fehlen).
    |  2. Ausgabe-Verzeichnis der Geschichte (Optional, Default: './out')
    |  3. Ausgabe-Verzeichnis der verschiedenen Übersichten (Optional, Default: './out')
    |
""".trimMargin()

private val DEFAULT_OUT = "out"

suspend fun main(args: Array<String>) {
    internalMain(args)
    logger.debug("ENTER drücken zum Beenden.")
    readLine()

}

suspend fun internalMain(args: Array<String>) {
    printMeasuredTimeAndReturnResult("Gesamt-Konvertierung") {
        val storyOutputDir = File(args.getOrElse(1) { DEFAULT_OUT }).apply { mkdirs() }
        val statistikenOutputDir = File(args.getOrElse(2) { DEFAULT_OUT }).apply { mkdirs() }

        val inputFiles = if (args.isEmpty()) {
            logger.info(parameterDescription)
            logger.info("Gebe Beispiel-Datei aus.")
            val beispielRessource = "beispiel.yaml"
            val beispielDatei = File(storyOutputDir, beispielRessource)
            ClassLoader.getSystemClassLoader().getResourceAsStream(beispielRessource)
                .copyTo(beispielDatei.outputStream())
            arrayOf(beispielDatei)
        } else {
            val inputFile = File(args[0])
            if (inputFile.isDirectory) {
                logger.info("Lese Dateien in Verzeichnis $inputFile.")
                inputFile.listFiles { _, file -> file.endsWith("yaml") || file.endsWith("json") }
            } else {
                logger.info("Lese Datei $inputFile.")
                arrayOf(inputFile)
            }
        }

        convert(inputFiles, storyOutputDir, statistikenOutputDir)
        printSumTime()
    }
}

@Suppress("DeferredResultUnused")
private suspend fun convert(
    inputFiles: Array<File>,
    storyOutputDir: File,
    statistikenOutputDir: File
) {
    coroutineScope {

        logger.info("Story-Ausgabe-Verzeichnis: " + storyOutputDir.absolutePath)
        logger.info("Ausgabe-Verzeichnis für den Rest: " + storyOutputDir.absolutePath)

        val velocity = async { initVelocity() }

        val gruppenDaten = ladeGruppenDaten(inputFiles)

        val nscs = ladeNscs(inputFiles)

        val nameBasis = inputFiles[0].nameWithoutExtension.substringBefore('(').trim()

        val nscCards = async {
            NscsFileWriter().writeData(gruppenDaten, nscs)
        }

        async {
            val htmlFile = File(storyOutputDir, nameBasis + ".html")
            val html = ErlebnisseHtmlFileWriter().writeData(gruppenDaten, nscs)
            velocity.await()
            generateHtml(htmlFile, html + nscCards.await(), gruppenDaten)
        }

        async {
            val htmlFile = File(statistikenOutputDir, nameBasis + "_APs.html")
            val html = ApsHtmlFileWriter().writeData(gruppenDaten, nscs)
            velocity.await()
            generateHtml(htmlFile, html, gruppenDaten)
        }

        async {
            val htmlFile = File(statistikenOutputDir, nameBasis + "_NSCs.html")
            velocity.await()
            generateHtml(htmlFile, nscCards.await(), gruppenDaten)
        }

        async {
            val htmlChronik = File(statistikenOutputDir, nameBasis + "_Chronik.html")
            ChronikHtmlFileWriter(htmlChronik).writeData(gruppenDaten)
        }
    }
}


private suspend fun CoroutineScope.ladeGruppenDaten(inputFiles: Array<File>): GruppenDaten {
    return inputFiles
        .sorted()
        .filter { !it.name.contains("NSC") }
        .map {
            async { loadDataFile<GruppenDaten>(it) }
        }
        .map { it.await() }
        .map { passeSystemInAbendenAn(it) }
        .reduce { gruppe1, gruppe2 ->
            GruppenDaten(
                gruppe2.gruppe,
                gruppe2.mitglieder,
                gruppe2.titel,
                gruppe2.verfasser,
                gruppe2.einleitung,
                gruppe1.abende + gruppe2.abende,
                gruppe2.system
            )
        }
}

private fun passeSystemInAbendenAn(gruppe: GruppenDaten): GruppenDaten {
    return if (gruppe.system == RpgSystem.UNDEFINIERT) {
        // nichts übergeordnetes definiert, es bleibt alles wie es ist
        gruppe
    } else {
        gruppe.copy(abende = gruppe.abende.map {
            if (it.system == RpgSystem.UNDEFINIERT) {
                // system ist im Abend nicht definiert, wir setzen ihn auf den globalen Wert
                it.copy(system = gruppe.system)
            } else {
                // der Wert ist gesetzt und wird nicht geändert
                it
            }
        })
    }
}


private suspend fun CoroutineScope.ladeNscs(inputFiles: Array<File>): List<Nsc> {
    return inputFiles
        .filter { it.name.contains("NSC") }
        .map {
            async { loadDataFile<Array<Nsc>>(it) }
        }
        .map { it.await() }
        .flatMap { it.toList() }
}

fun generateHtml(htmlFile: File, body: String, gruppenDaten: GruppenDaten) =
    printMeasuredTimeAndReturnResult("Generieren von ${htmlFile.name} aus dem Template") {
        val context = VelocityContext().apply {
            put("gruppenDaten", gruppenDaten)
            put("body", body)
            put(
                "jetzt",
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN))
            )
            put("containerTyp", "container-fluid")
            put("sidebar", true)
        }
        htmlFile.writer().use { TEMPLATE.merge(context, it) }
    }





