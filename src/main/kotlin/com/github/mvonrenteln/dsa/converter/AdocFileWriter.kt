package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.FileOutputStream
import java.io.Writer
import kotlin.properties.Delegates.notNull
import kotlin.system.measureTimeMillis

abstract class AdocFileWriter(val adocFile: File) {

    protected var writer: Writer by notNull()

    init {
        adocFile.delete()
    }

    fun writeData(gruppenDaten: GruppenDaten) {
        val time = measureTimeMillis {
            FileOutputStream(adocFile, true).writer().buffered().use {
                this.writer = it
                writeDataInternal(gruppenDaten)
            }
        }
        println("In ADOC file geschrieben in $time ms.")
    }

    protected abstract fun writeDataInternal(daten: GruppenDaten)

    companion object {
        val BR = "\r\n"
        val LEERZEILE = "$BR$BR"
        val TABLE_DELIMITER = "|===$BR"
        val NONBREAKING_SPACE = "{nbsp}"
    }
}