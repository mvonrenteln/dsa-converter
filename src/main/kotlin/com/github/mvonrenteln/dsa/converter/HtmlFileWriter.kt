package com.github.mvonrenteln.dsa.converter

import java.io.StringWriter
import kotlin.system.measureTimeMillis

abstract class HtmlFileWriter {

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
        writer.append("<h1>$titel</h1>$LEERZEILE")
    }

    protected fun h2(titel: String) {
        writer.append("<h2>$titel</h2>$LEERZEILE")
    }

    protected fun h3(titel: String) {
        writer.append("<h3>$titel<h3>$LEERZEILE")
    }

    protected fun h4(titel: String) {
        writer.append("<h4>$titel<h4>$LEERZEILE")
    }

    protected fun zeile(text: String?) {
        if (text != null) {
            writer.append("$text$BR")
        }
    }

    protected fun textblock(text: String?) {
        if (text != null) {
            writer.append("${text.toHtml()}$LEERZEILE")
        }
    }

    protected fun leerzeile() {
        writer.append(LEERZEILE)
    }

    protected fun inhaltsverzeichnis() {
        writer.append("[TOC]$LEERZEILE")
    }

    protected fun tabellenÜberschrift(vararg spalten: Any?) {
        zeile("""<table class="table table-striped table-hover">""")
        zeile("<tr>")
        spalten.forEach { writer.append("<th>${it.oderLeer()}</th>") }
        zeile("</tr>")
    }

    protected fun tabellenZeile(vararg spalten: Any?) {
        zeile("<tr>")
        spalten.forEach { writer.append("<td>${it.oderLeer()}</td>") }
        leerzeile()
        zeile("</tr>")
    }

    protected fun tabellenFazit(vararg spalten: Any?) {
        zeile("<tr>")
        spalten.forEach { writer.append("<td><b>${it.oderLeer()}</b></td>") }
        leerzeile()
        zeile("</tr>")
    }

    protected fun tabellenZelleMitTitel(inhalt: String, titel: String): String {
        return """<div data-toggle="popover" data-trigger="hover" title="$inhalt" data-content="$titel">$inhalt</div>"""
    }

    protected fun tabellenEnde() {
        zeile("</table>")
    }

    protected fun String.toHtml(): String {
        val document = PARSER.parse(this)
        return RENDERER.render(document)
    }

    private fun Any?.oderLeer() = this?.toString() ?: LEER


    companion object {
        private val BR = "\r\n"
        private val LEERZEILE = "$BR$BR"
        val LEER = "&nbsp;"
    }
}