package com.github.mvonrenteln.dsa.converter.json

import com.github.mvonrenteln.dsa.converter.GruppenDaten
import com.github.mvonrenteln.dsa.converter.loadDataFile
import com.github.mvonrenteln.dsa.converter.printMeasuredTimeAndReturnResult
import com.github.mvonrenteln.dsa.converter.writeYamlFile
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

