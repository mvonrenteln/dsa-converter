package com.github.mvonrenteln.dsa.converter

import java.io.StringWriter
import java.io.Writer

abstract class HtmlFileWriter {

    protected var writer: Writer = StringWriter()

    private val kapitel = mutableMapOf<String, String>()

    fun writeData(gruppenDaten: GruppenDaten, nscs: List<Nsc>): String {
        printMeasuredTimeAndReturnResult("HTML geschrieben") {
            section {
                writeDataInternal(gruppenDaten, nscs)
            }
        }
        return writer.toString()
    }

    protected abstract fun writeDataInternal(gruppenDaten: GruppenDaten, nscs: List<Nsc>)

    protected fun h1(titel: String, untertitel: String? = null) {
        val untertitelTag = if (untertitel != null) " <small>$untertitel</small>" else ""
        writer.append("""<h1 id="${alsId(titel)}">$titel$untertitelTag</h1>$LEERZEILE""")
    }

    protected fun h2(titel: String) {
        writer.append("""<h2 id="${alsId(titel)}">$titel</h2>$LEERZEILE""")
    }

    protected fun h3(titel: String) {
        writer.append("""<h3 id="${alsId(titel)}">$titel</h3>$LEERZEILE""")
    }

    protected fun h4(titel: String) {
        writer.append("""<h4 id="${alsId(titel)}">$titel</h4>$LEERZEILE""")
    }

    protected fun alsId(titel: String): String {
        var id = titel.toLowerCase().onlyWordCharacters()

        if (kapitel.containsKey(id)) {
            id += Math.random().toString().substring(2..5)
        }
        kapitel.put(id, titel)

        return id
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

    protected fun section(block: () -> Unit) {
        zeile("""<section>""")
        block()
        zeile("</section>")
    }

    protected fun tabelle(vararg spalten: Any?, block: () -> Unit) {
        zeile("""<table class="table table-striped table-hover">""")
        zeile("<tr>")
        spalten.forEach { writer.append("<th>${it.oderLeer()}</th>") }
        zeile("</tr>")
        block()
        zeile("</table>")
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

    protected fun popover(inhalt: String, popover: String? = null, id: String? = null): String {
        return if (popover.isNullOrBlank() && id.isNullOrBlank())
            inhalt
        else if (!popover.isNullOrBlank()) {
            """<abbr data-toggle="popover" data-trigger="hover" title="$inhalt" data-content="$popover">$inhalt</abbr>"""
        } else {
            """<abbr data-toggle="popover" data-trigger="hover" title="$inhalt" data-popover-content="#$id">$inhalt</abbr>"""
        }
    }

    protected fun String.toHtml(): String {
        val document = PARSER.parse(this)
        return RENDERER.render(document).replace("+\n", "<BR/>")
    }

    private fun Any?.oderLeer() = this?.toString() ?: LEER


    companion object KLogging {
        private val BR = "\n"
        private val LEERZEILE = "$BR$BR"
        val LEER = "&nbsp;"
    }
}