package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.InputStream
import kotlin.system.measureTimeMillis

val parameterDescription = "Parameter: 1. Name der Eingabe-Datei, 2. Ausgabe-Verzeichnis (Optional, Default: 'out')"

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        if (args.isEmpty()) {
            println(parameterDescription)
            println("Gebe Beispiel-Datei aus.")
            val inputFileName = "example.yaml"
            val input = ClassLoader.getSystemClassLoader().getResourceAsStream(inputFileName)
            convert(inputFileName, input, File("out"))
        } else {
            val inputFileName = args[0]
            val input = File(inputFileName).inputStream()
            val outputDir = if (args.size > 1) args[1] else "out"
            convert(inputFileName, input, File(outputDir))
        }
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
    println("ENTER drücken zum Beenden.")
    readLine()

}

private fun convert(inputFileName: String, inputStream: InputStream, outputDir: File) {
    val adocFile = File(outputDir, File(inputFileName).nameWithoutExtension + ".adoc")
    val data = loadDataFile<GruppenDaten>(inputStream, inputFileName)

    StoryAdocFileWriter(adocFile).writeData(data)
    ASCIIDOCTOR.convertFile(adocFile)
}





