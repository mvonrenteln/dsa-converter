package com.github.mvonrenteln.dsa.converter

class StoryHtmlFileWriter : HtmlFileWriter() {

    private var aktuellesAbenteuer = ""

    private var aktuellesDatum: String? = null


    override fun writeDataInternal(gruppenDaten: GruppenDaten) {
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

                textblock(abschnitt.text)
            }
        }
    }

}