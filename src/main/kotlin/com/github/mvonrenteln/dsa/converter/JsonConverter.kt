package com.github.mvonrenteln.dsa.converter

import java.io.File
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val input = File(args[0])
        val outputDir = if (args.size > 1) args[1] else "out"
        convert(input, File(outputDir))
    }
    logger.debug("Gesamt-Konvertierung in $time ms abgeschlossen.")
}

private fun convert(inputFile: File, outputDir: File) {
    val daten = loadDataFile<GruppenDaten>(inputFile)

    writeYamlFile(daten, File(outputDir, inputFile.nameWithoutExtension + ".yaml"))
}

