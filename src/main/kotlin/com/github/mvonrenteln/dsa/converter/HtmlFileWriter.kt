package com.github.mvonrenteln.dsa.converter

import java.io.StringWriter
import java.io.Writer

abstract class HtmlFileWriter(private val typ: String) {

    protected var writer: Writer = StringWriter()

    private val kapitel = mutableMapOf<String, String>()

    fun writeData(gruppenDaten: GruppenDaten, nscs: List<Nsc>): String {
        printMeasuredTimeAndReturnResult("$typ-HTML geschrieben") {
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
        kapitel[id] = titel

        return id
    }

    private fun zeile(text: String?) {

        if (text != null) {
            writer.append("$text$BR")
        }
    }

    protected fun textblock(text: String?) {
        if (text != null) {
            writer.append("${text.toHtml()}$LEERZEILE")
        }
    }

    private fun leerzeile() {
        writer.append(LEERZEILE)
    }

    private fun section(block: () -> Unit) {
        zeile("""<section>""")
        block()
        zeile("</section>")
    }

    @Suppress("SameParameterValue")
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

    protected fun popover(inhalt: String, popover: String? = null, id: String? = null, titel: String = inhalt): String {
        return if (popover.isNullOrBlank() && id.isNullOrBlank())
            inhalt
        else {
            val dataField = if (!popover.isNullOrBlank()) {
                """data-content="$popover" """
            } else {
                """data-popover-content="#$id" """
            }
            """<abbr data-toggle="popover" data-trigger="hover" title="$titel" $dataField>$inhalt</abbr>"""
        }
    }

    @Suppress("SameParameterValue")
    private fun String.toHtml(): String {
        val document = PARSER.parse(this)
        return RENDERER.render(document).replace("+\n", "<BR/>")
    }

    private fun Any?.oderLeer() = this?.toString() ?: LEER


    companion object KLogging {
        private const val BR = "\n"
        private const val LEERZEILE = "$BR$BR"
        const val LEER = "&nbsp;"
    }
}