package com.github.mvonrenteln.dsa.converter

import java.io.StringWriter
import kotlin.system.measureTimeMillis

abstract class AdocFileWriter {

    private var writer = StringWriter()

    fun writeData(gruppenDaten: GruppenDaten): String {
        val time = measureTimeMillis {
            writeDataInternal(gruppenDaten)
        }
        println("Markdown geschrieben in $time ms.")
        return writer.toString()
    }


    protected abstract fun writeDataInternal(gruppenDaten: GruppenDaten)

    protected fun h1(titel: String) {
        writer.append("# $titel$BR")
    }

    protected fun h2(titel: String) {
        writer.append("## $titel$LEERZEILE")
    }

    protected fun h3(titel: String) {
        writer.append("### $titel$LEERZEILE")
    }

    protected fun h4(titel: String) {
        writer.append("#### $titel$LEERZEILE")
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
        writer.append("[TOC]$LEERZEILE")
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