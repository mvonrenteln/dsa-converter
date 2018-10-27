package com.github.mvonrenteln.dsa.converter.legacy

import com.github.mvonrenteln.dsa.converter.*
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    printMeasuredTimeAndReturnResult("Gesamt-Konvertierung") {
        val input = File(args[0])
        val outputDir = if (args.size > 1) args[1] else "out"
        convert(input, File(outputDir))
    }
}

private fun convert(inputFile: File, outputDir: File) {
    val altDaten = loadDataFile<LegacyGruppenDaten>(inputFile)

    val neueDaten = GruppenDaten(gruppe = altDaten.gruppe, abende = altDaten.abende.map {
        Abend(
            kampagne = "Die sieben Gezeichneten",
            abenteuer = it.abenteuer.trim(),
            datum = konvertiereDatum(it.datum),
            spieldauer = berechneSpieldauer(it.aps),
            titel = it.titel.trim(),
            zitat = it.zitat?.bereinige(),
            daten = it.daten?.bereinige()?.entferneUnnötigeDaten(),
            aps = it.aps,
            abschnitte = listOf(Abschnitt(datum = "Unbekannt", ort = "Unbekannt", text = it.text.bereinige()))
        )
    })

    writeYamlFile(neueDaten, File(outputDir, inputFile.nameWithoutExtension + ".yaml"))
}

fun berechneSpieldauer(aps: List<AP>): Int {
    val charakterreife = aps.firstOrNull { it.beschreibung == "Charakterreife" }
    return if (charakterreife != null) charakterreife.aps / 10 else 0
}

fun konvertiereDatum(datum: String): String {
    val localDate = LocalDate.parse(datum.substring(0..9))
    return localDate.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"))
}

fun String.bereinige() = this.replace("\r", "").trim()

fun String.entferneUnnötigeDaten() =
    this.removeRange("AP", "Gesamt")
        .entferneAllesAb("Liebe Grüße")
        .trim()

