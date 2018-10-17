package com.github.mvonrenteln.dsa.converter

import java.io.File

class ApsAdocFileWriter(adocFile: File) : AdocFileWriter(adocFile) {

    override fun writeDataInternal(gruppenDaten: GruppenDaten) {
        val erlebnisse = berechneAps(gruppenDaten)
        writer.append("= APs der ${erlebnisse.name}$BR:toc:$LEERZEILE")
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
        writer.append("== " + ueberschrift + LEERZEILE)
        writer.append(TABLE_DELIMITER + "|$tabellenTitel|APs|davon Charakterreife|Spieldauer$LEERZEILE")
    }


    private fun neuerAbend(abend: AbendAps) {
        writer.append("""|+++<p class="tableblock" title="${abend.zusammenfassung}">${abend.titel}</p>+++|${abend.aps}|${abend.charakterreife}|${abend.spieldauer} Stunden$BR""")
    }


    private fun abschnittAbschliessen(abenteuer: Abenteuer) {
        writer.append(
            """|*Durchschnitt*|*${abenteuer.apsDurchschnitt()}${apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer)}*
                     |*${abenteuer.characterreifeDurchschnitt()}*
                     |*${abenteuer.spieldauerDurchschnitt()} Stunden*$BR"""
        )
        writer.append("|*Gesamt*|*${abenteuer.aps}*|*${abenteuer.charakterreife}*|*${abenteuer.abende} Abende (${abenteuer.spieldauer} Stunden)*$BR")
        writer.append(TABLE_DELIMITER + BR)
    }


    private fun apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer: Abenteuer): String {
        return " (${abenteuer.apsDurchschnittBeiDurchschnittlicherSpieldauer()} f&uuml;r ${abenteuer.spieldauerDurchschnitt()} Stunden)"
    }

}