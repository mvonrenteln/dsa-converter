package com.github.mvonrenteln.dsa.converter

import java.io.File

class ChronikAdocFileWriter(adocFile: File) : AdocFileWriter(adocFile) {

    private var aktuellesAbenteuer = ""

    private var aktuellesKapitel = ""

    private var aktuellesDatum: String? = null


    override fun writeDataInternal(gruppenDaten: GruppenDaten) {
        h1("Chronik der ${gruppenDaten.gruppe}")
        leerzeile()

        tabellenÜberschrift("Datum", "Ort", "Personen", "Zusammenfassung", "Abenteuer", "Kapitel", "Abend")
        gruppenDaten.abende.forEach { abend ->
            var istErsterAbschnitt = true
            abend.abschnitte.forEach { abschnitt ->
                tabellenZeile(
                    datum(abschnitt),
                    abschnitt.ort,
                    personen(abschnitt),
                    abschnitt.zusammenfassung,
                    abenteuer(abschnitt, abend),
                    kapitel(abschnitt),
                    if (istErsterAbschnitt) abend.titel else LEER
                )
                istErsterAbschnitt = false
            }
        }
    }

    fun datum(abschnitt: Abschnitt) =
        if (aktuellesDatum != abschnitt.datum) {
            abschnitt.datum.also { aktuellesDatum = it }
        } else {
            LEER
        }

    fun abenteuer(abschnitt: Abschnitt, abend: Abend) =
        if (abschnitt.abenteuer != null && aktuellesAbenteuer != abschnitt.abenteuer) {
            abschnitt.abenteuer.also { aktuellesAbenteuer = it }
        } else if (aktuellesAbenteuer != abend.abenteuer) {
            abend.abenteuer.also { aktuellesAbenteuer = it }
        } else {
            LEER
        }

    fun kapitel(abschnitt: Abschnitt) =
        if (abschnitt.kapitel != null && aktuellesKapitel != abschnitt.kapitel) {
            abschnitt.kapitel.also { aktuellesKapitel = it }
        } else {
            LEER
        }

    fun personen(abschnitt: Abschnitt) =
        (abschnitt.personen.orEmpty() + abschnitt.gruppen.orEmpty()).joinToString(transform = orEmpty)

    private val orEmpty: (String?) -> CharSequence = { it.orEmpty() }
}
