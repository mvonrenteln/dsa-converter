package com.github.mvonrenteln.dsa.converter

class ApsHtmlFileWriter : HtmlFileWriter() {

    override fun writeDataInternal(gruppenDaten: GruppenDaten, nscs: List<Nsc>) {
        val erlebnisse = berechneAps(gruppenDaten)
        h1("APs der ${erlebnisse.name}")
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
                zusammenfassung = abend.abschnitte.map { it.zusammenfassung }.filterNotNull().joinToString(" ")
            )
            abenteuer.abendHinzufuegen(derAbend)
        }
        erlebnisse.abenteuerHinzufuegen(abenteuer)
        return erlebnisse
    }


    private fun zusammenfassung(erlebnisse: Erlebnisse) {
        h2("Zusammenfassung")
        apTabelle(LEER) {
            abschnittAbschliessen(erlebnisse)
        }
    }


    private fun neuesAbenteuer(abenteuer: Abenteuer) {
        h2(abenteuer.name)
        apTabelle("Abschnitt") {
            abenteuer.abendeDetails.forEach { neuerAbend(it) }
            abschnittAbschliessen(abenteuer)
        }
    }

    private fun apTabelle(titel: String, block: () -> Unit) {
        tabelle(titel, "APs", "davon Charakterreife", "Spieldauer") { block() }
    }


    private fun neuerAbend(abend: AbendAps) {
        tabellenZeile(
            popover(abend.titel, abend.zusammenfassung),
            abend.aps,
            abend.charakterreife,
            "${abend.spieldauer} Stunden"
        )
    }


    private fun abschnittAbschliessen(abenteuer: Abenteuer) {
        tabellenFazit(
            "Durchschnitt",
            "${abenteuer.apsDurchschnitt()}",
            abenteuer.characterreifeDurchschnitt(),
            "${abenteuer.spieldauerDurchschnitt()} Stunden"
        )
        tabellenFazit(
            "Gesamt",
            abenteuer.aps,
            abenteuer.charakterreife,
            "${abenteuer.abende} Abende (${abenteuer.spieldauer} Stunden)"
        )
    }

}