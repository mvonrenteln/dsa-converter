package com.github.mvonrenteln.dsa.converter

import java.io.File

fun main(args: Array<String>) {
    printMeasuredTimeAndReturnResult("Gesamt-Konvertierung") {
        val input = File(args[0])
        val outputDir = if (args.size > 1) args[1] else "out"
        convert(input, File(outputDir))
    }
}

private fun convert(inputFile: File, outputDir: File) {
    val daten = loadDataFile<GruppenDaten>(inputFile)

    writeYamlFile(daten, File(outputDir, inputFile.nameWithoutExtension + ".yaml"))
}

