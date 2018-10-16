package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.InputStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val inputFileName = args[0]
        val input = File(inputFileName).inputStream()
        val outputDir = if (args.size > 1) args[1] else "out"
        convert(inputFileName, input, File(outputDir))
    }
    println("Gesamt-Konvertierung in $time ms abgeschlossen.")
}

private fun convert(inputFileName: String, inputStream: InputStream, outputDir: File) {
    val daten = loadDataFile<GruppenDaten>(inputStream, inputFileName)

    writeYamlFile(daten, File(outputDir, File(inputFileName).nameWithoutExtension + ".yaml"))
}

