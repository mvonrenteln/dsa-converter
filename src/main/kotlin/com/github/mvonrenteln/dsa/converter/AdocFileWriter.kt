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

    protected fun h1(titel: String) {
        writer.append("= $titel$BR")
    }

    protected fun h2(titel: String) {
        writer.append("== $titel$LEERZEILE")
    }

    protected fun h3(titel: String) {
        writer.append("=== $titel$LEERZEILE")
    }

    protected fun h4(titel: String) {
        writer.append("==== $titel$LEERZEILE")
    }

    protected fun zeile(text: String?) {
        if (text != null) {
            writer.append("$text$BR")
        }
    }

    protected fun textblock(text: String?) {
        if (text != null) {
            writer.append("$text$LEERZEILE")
        }
    }

    protected fun inhaltsverzeichnis() {
        writer.append(":toc:$LEERZEILE")
    }

    companion object {
        val BR = "\r\n"
        val LEERZEILE = "$BR$BR"
        val TABLE_DELIMITER = "|===$BR"
        val NONBREAKING_SPACE = "{nbsp}"
    }
}