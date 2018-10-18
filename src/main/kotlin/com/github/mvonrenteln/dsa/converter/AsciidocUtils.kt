package com.github.mvonrenteln.dsa.converter

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import java.io.File
import kotlin.system.measureTimeMillis

fun initAsciidoctor(): Asciidoctor {
    return printMeasuredTimeAndReturnResult("AsciidoctorJ initialisiert in %d ms.") {
        Asciidoctor.Factory.create()
    }
}

val OPTIONS = Options().apply {
    setInPlace(true)
    setSafe(SafeMode.UNSAFE)
}

fun Asciidoctor.convertFile(adocFile: File) {
    val time = measureTimeMillis {
        convertFile(adocFile, OPTIONS)
    }
    println("${adocFile.name} in HTML konvertiert in $time ms.")
}