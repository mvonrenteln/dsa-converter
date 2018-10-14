package com.github.mvonrenteln.dsa.converter

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import java.io.File

fun writeAsciidocFile(adocFile: File) {
    val options = Options()
    options.setInPlace(true)
    options.setSafe(SafeMode.UNSAFE)
    Asciidoctor.Factory.create().convertFile(adocFile, options)
}