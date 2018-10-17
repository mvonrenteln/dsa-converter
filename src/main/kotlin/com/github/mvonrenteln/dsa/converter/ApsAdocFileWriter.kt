package com.github.mvonrenteln.dsa.converter

import java.io.File

class ApsAdocFileWriter(adocFile: File) : AdocFileWriter(adocFile) {

    override fun writeDataInternal(gruppenDaten: GruppenDaten) {
        val erlebnisse = berechneAps(gruppenDaten)
        h1("APs der ${erlebnisse.name}")
        inhaltsverzeichnis()
        zusammenfassung(erlebnisse)
        erlebnisse.abenteuer.forEach { neuesAbenteuer(it) }
    }


    fun berechneAps(gruppenDaten: GruppenDaten): Erlebnisse {
        var abenteuer: Abenteuer = Abenteuer.LEER

        val erlebnisse = Erlebnisse(gruppenDaten.gruppe)

        gruppenDaten.abende.forEach { abend ->
            val abenteuerName = abend.abenteuer.trim()
            if (abenteuer.name != abenteuerName) {
                erlebnisse.abenteuerHinzufuegen(abenteuer)
                abenteuer = Abenteuer(abenteuerName)
            }

            val derAbend = AbendAps(
                titel = abend.titel,
                aps = abend.aps.map { it.aps }.sum(),
                charakterreife = abend.aps[0].aps,
                spieldauer = abend.spieldauer,
                zusammenfassung = abend.abschnitte.map { it.zusammenfassung }.joinToString(" ")
            )
            abenteuer.abendHinzufuegen(derAbend)
        }
        erlebnisse.abenteuerHinzufuegen(abenteuer)
        return erlebnisse
    }


    private fun zusammenfassung(erlebnisse: Erlebnisse) {
        abschnittBeginnen("Zusammenfassung", NONBREAKING_SPACE)
        abschnittAbschliessen(erlebnisse)
    }


    private fun neuesAbenteuer(abenteuer: Abenteuer) {
        abschnittBeginnen(abenteuer.name, "Abschnitt")
        abenteuer.abendeDetails.forEach { neuerAbend(it) }
        abschnittAbschliessen(abenteuer)
    }


    private fun abschnittBeginnen(ueberschrift: String, tabellenTitel: String) {
        h2(ueberschrift)
        zeile(TABLE_DELIMITER + "|$tabellenTitel|APs|davon Charakterreife|Spieldauer")
    }


    private fun neuerAbend(abend: AbendAps) {
        zeile("""|+++<p class="tableblock" title="${abend.zusammenfassung}">${abend.titel}</p>+++|${abend.aps}|${abend.charakterreife}|${abend.spieldauer} Stunden""")
    }


    private fun abschnittAbschliessen(abenteuer: Abenteuer) {
        zeile(
            """|*Durchschnitt*|*${abenteuer.apsDurchschnitt()}${apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer)}*
                     |*${abenteuer.characterreifeDurchschnitt()}*
                     |*${abenteuer.spieldauerDurchschnitt()} Stunden*"""
        )
        zeile("|*Gesamt*|*${abenteuer.aps}*|*${abenteuer.charakterreife}*|*${abenteuer.abende} Abende (${abenteuer.spieldauer} Stunden)*")
        zeile(TABLE_DELIMITER)
    }


    private fun apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer: Abenteuer): String {
        return " (${abenteuer.apsDurchschnittBeiDurchschnittlicherSpieldauer()} f&uuml;r ${abenteuer.spieldauerDurchschnitt()} Stunden)"
    }

}