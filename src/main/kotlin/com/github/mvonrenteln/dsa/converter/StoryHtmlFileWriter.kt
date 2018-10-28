package com.github.mvonrenteln.dsa.converter

class StoryHtmlFileWriter : HtmlFileWriter("Story") {

    private var aktuellesAbenteuer = ""

    private var aktuellesDatum: String? = null


    override fun writeDataInternal(gruppenDaten: GruppenDaten, nscs: List<Nsc>) {
        h1("${gruppenDaten.titel} (${gruppenDaten.gruppe})", gruppenDaten.verfasser)

        textblock(gruppenDaten.einleitung)

        gruppenDaten.abende.forEach { abend ->

            if (aktuellesAbenteuer != abend.abenteuer) {
                aktuellesAbenteuer = abend.abenteuer
                h2(aktuellesAbenteuer)
            }

            h3(abend.titel)

            textblock(abend.zitat)

            abend.abschnitte.forEach { abschnitt ->
                if (abschnitt.abenteuer != null && aktuellesAbenteuer != abschnitt.abenteuer) {
                    aktuellesAbenteuer = abschnitt.abenteuer
                    h2(aktuellesAbenteuer)
                    h3(abend.titel)
                }
                if (aktuellesDatum != abschnitt.datum) {
                    aktuellesDatum = abschnitt.datum
                    h4(abschnitt.datum)
                }

                textblock(ersetzeNscs(abschnitt.text, nscs))
            }
        }
    }

    private fun ersetzeNscs(text: String, nscs: List<Nsc>): String {
        var neuerText = text
        for (nsc in nscs) {
            if (nsc.vorname != null) {

                neuerText = neuerText.replace(
                    nsc.vorname,
                    popover(inhalt = nsc.vorname, titel = nsc.ganzerName(), id = nsc.id)
                )
            }
        }
        return neuerText
    }

}