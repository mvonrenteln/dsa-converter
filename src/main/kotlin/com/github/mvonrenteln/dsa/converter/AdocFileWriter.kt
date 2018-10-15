package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.FileOutputStream
import java.io.Writer
import kotlin.system.measureTimeMillis

abstract class AdocFileWriter(val adocFile: File) {

    val BR = "\r\n"
    val LEERZEILE = "$BR$BR"

    init {
        adocFile.delete()
    }

    fun writeData(gruppenDaten: GruppenDaten) {
        val time = measureTimeMillis {
            FileOutputStream(adocFile, true).writer().buffered().use {
                writeDataInternal(gruppenDaten, it)
            }
        }
        println("In ADOC file geschrieben in $time ms.")
    }

    protected abstract fun writeDataInternal(gruppenDaten: GruppenDaten, writer: Writer)
}