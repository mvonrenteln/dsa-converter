package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.InputStream
import kotlin.system.measureTimeMillis

val parameterDescription = """Parameter: 1. Name der Eingabe-Datei,
    |2. Ausgabe-Verzeichnis der Geschichte (Optional, Default: 'out')
    |3. Ausgabe-Verzeichnis der AP-Übersicht (Optional, Default: 'out')
""".trimMargin()

private val DEFAULT_OUT = "out"

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        if (args.isEmpty()) {
            println(parameterDescription)
            println("Gebe Beispiel-Datei aus.")
            val inputFileName = "example.yaml"
            val input = ClassLoader.getSystemClassLoader().getResourceAsStream(inputFileName)
            convert(inputFileName, input, DEFAULT_OUT, DEFAULT_OUT)
        } else {
            val inputFileName = args[0]
            val input = File(inputFileName).inputStream()
            val storyOutputDir = args.getOrElse(1) { DEFAULT_OUT }
            val apsOutputDir = args.getOrElse(2) { DEFAULT_OUT }
            convert(inputFileName, input, storyOutputDir, apsOutputDir)
        }
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
    println("ENTER drücken zum Beenden.")
    readLine()

}

private fun convert(inputFileName: String, inputStream: InputStream, storyOutputDir: String, apsOutputDir: String) {
    val data = loadDataFile<GruppenDaten>(inputStream, inputFileName)

    val adocStoryFile = File(storyOutputDir, File(inputFileName).nameWithoutExtension + ".adoc")
    StoryAdocFileWriter(adocStoryFile).writeData(data)
    ASCIIDOCTOR.convertFile(adocStoryFile)

    val adocApsFile = File(apsOutputDir, File(inputFileName).nameWithoutExtension + "_APs.adoc")
    ApsAdocFileWriter(adocApsFile).writeData(data)
    ASCIIDOCTOR.convertFile(adocApsFile)
}





