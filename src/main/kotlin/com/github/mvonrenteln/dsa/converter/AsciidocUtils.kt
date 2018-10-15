package com.github.mvonrenteln.dsa.converter

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import java.io.File
import kotlin.system.measureTimeMillis

val ASCIIDOCTOR by lazy {
    printMeasuredTimeAndReturnResult("AsciidoctorJ initialisiert in %d ms.") {
        Asciidoctor.Factory.create()
    }
}

val OPTIONS = Options().apply {
    this.setInPlace(true)
    this.setSafe(SafeMode.UNSAFE)
}

fun Asciidoctor.convertFile(adocFile: File) {
    val time = measureTimeMillis {
        this.convertFile(adocFile, OPTIONS)
    }
    println("ADOC in HTML konvertiert in $time ms.")
}