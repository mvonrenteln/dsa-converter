package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.FileOutputStream
import java.io.Writer
import kotlin.properties.Delegates.notNull
import kotlin.system.measureTimeMillis

abstract class AdocFileWriter(val adocFile: File) {

    private var writer: Writer by notNull()

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
        println("In ${adocFile.name} geschrieben in $time ms.")
    }

    protected abstract fun writeDataInternal(gruppenDaten: GruppenDaten)

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

    protected fun leerzeile() {
        writer.append(LEERZEILE)
    }

    protected fun inhaltsverzeichnis() {
        writer.append(":toc:$LEERZEILE")
    }

    protected fun tabellenÜberschrift(vararg spalten: Any?) {
        zeile(TABLE_DELIMITER)
        tabellenZeile(*spalten)
    }

    protected fun tabellenZeile(vararg spalten: Any?) {
        zeile(spalten.joinToString(separator = "|", prefix = "|", transform = oderLeer))
    }

    protected fun tabellenFazit(vararg spalten: Any?) {
        zeile(spalten.joinToString(separator = "*|*", prefix = "|*", postfix = "*", transform = oderLeer))
    }

    protected fun tabellenZelleMitTitel(inhalt: String, titel: String): String {
        return """+++<p class="tableblock" title="$titel">$inhalt</p>+++"""
    }

    protected fun tabellenEnde() {
        zeile(TABLE_DELIMITER)
    }

    private val oderLeer: (Any?) -> CharSequence = { it?.toString() ?: LEER }


    companion object {
        private val BR = "\r\n"
        private val LEERZEILE = "$BR$BR"
        private val TABLE_DELIMITER = "|==="
        val LEER = "{nbsp}"
    }
}