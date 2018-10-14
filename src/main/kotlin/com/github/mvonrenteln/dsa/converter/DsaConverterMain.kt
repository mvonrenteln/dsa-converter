package com.github.mvonrenteln.dsa.converter

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemClassLoader().getResource("DSA-Abenteuer_G7_clean.json").toURI())
    val adocFile = File("out", input.name.substring(0..input.name.length-6) + ".adoc")
    val data = loadJsonFile(input)

    AdocFileWriter(adocFile).writeData(data)
    writeAsciidocFile(adocFile)
}



