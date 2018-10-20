package com.github.mvonrenteln.dsa.converter

class ApsHtmlFileWriter : HtmlFileWriter() {

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
        abschnittBeginnen("Zusammenfassung", LEER)
        abschnittAbschliessen(erlebnisse)
    }


    private fun neuesAbenteuer(abenteuer: Abenteuer) {
        abschnittBeginnen(abenteuer.name, "Abschnitt")
        abenteuer.abendeDetails.forEach { neuerAbend(it) }
        abschnittAbschliessen(abenteuer)
    }


    private fun abschnittBeginnen(ueberschrift: String, tabellenTitel: String) {
        h2(ueberschrift)
        tabellenÜberschrift(tabellenTitel, "APs", "davon Charakterreife", "Spieldauer")
    }


    private fun neuerAbend(abend: AbendAps) {
        tabellenZeile(
            tabellenZelleMitTitel(abend.titel, abend.zusammenfassung),
            abend.aps,
            abend.charakterreife,
            "${abend.spieldauer} Stunden"
        )
    }


    private fun abschnittAbschliessen(abenteuer: Abenteuer) {
        tabellenFazit(
            "Durchschnitt",
            "${abenteuer.apsDurchschnitt()}${apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer)}",
            abenteuer.characterreifeDurchschnitt(),
            "${abenteuer.spieldauerDurchschnitt()} Stunden"
        )
        tabellenFazit(
            "Gesamt",
            abenteuer.aps,
            abenteuer.charakterreife,
            "${abenteuer.abende} Abende (${abenteuer.spieldauer} Stunden)"
        )
        tabellenEnde()
    }


    private fun apsDurchschnittBeiDurchschnittlicherSpieldauer(abenteuer: Abenteuer): String {
        return " (${abenteuer.apsDurchschnittBeiDurchschnittlicherSpieldauer()} f&uuml;r ${abenteuer.spieldauerDurchschnitt()} Stunden)"
    }

}