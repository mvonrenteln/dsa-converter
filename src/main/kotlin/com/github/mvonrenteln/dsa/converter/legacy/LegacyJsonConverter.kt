package com.github.mvonrenteln.dsa.converter.legacy

import com.github.mvonrenteln.dsa.converter.*
import com.github.mvonrenteln.dsa.converter.AP
import java.io.File
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val inputFileName = args[0]
        val input = File(inputFileName).inputStream()
        val outputDir = if (args.size > 1) args[1] else "out"
        convert(inputFileName, input, File(outputDir))
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
    println("ENTER drücken zum Beenden.")
    readLine()

}

fun convert(inputFileName: String, inputStream: InputStream, outputDir: File) {
    val altDaten = loadDataFile<LegacyGruppenDaten>(inputStream, inputFileName)

    val neueDaten = GruppenDaten(gruppe = altDaten.gruppe, abende = altDaten.abende.map {
        Abend(
            abenteuer = it.abenteuer,
            datum = konvertiereDatum(it.datum),
            spieldauer = 0,
            titel = it.titel,
            zitat = it.zitat?.bereinige(),
            daten = it.daten?.bereinige()?.entferneUnnötigeDaten(),
            aps = it.aps.map { AP(it.aps, it.beschreibung) },
            abschnitte = listOf(Abschnitt(datum = "Unbekannt", ort = "Unbekannt", text = it.text.bereinige()))
        )
    })

    writeYamlFile(neueDaten, File(outputDir, File(inputFileName).nameWithoutExtension + ".yaml"))
}

fun konvertiereDatum(datum: String): String {
    val localDate = LocalDate.parse(datum.substring(0..9))
    return localDate.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
}

fun String.bereinige() = this.replace("\r", "")

fun String.entferneUnnötigeDaten(): String {
    val entfernt = this.replace("Weitere SEs auf Anfrage!", "")
    val start = entfernt.indexOf("APs")
    val ende = entfernt.indexOf("Gesamt")
    val entfernt2 = if (start != -1 && ende != -1) entfernt.removeRange(start, ende + 6) else entfernt
    return entfernt2.trim()
}