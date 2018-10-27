package com.github.mvonrenteln.dsa.converter

import java.io.File

class ChronikHtmlFileWriter(val htmlFile: File) {

    private var aktuellesAbenteuer = ""

    private var aktuellesKapitel = ""

    private var aktuellesDatum: String = ""


    fun writeData(gruppenDaten: GruppenDaten) {
        printMeasuredTimeAndReturnResult("In ${htmlFile.name} geschrieben") {
            val htmlData = mutableListOf<List<String>>()
            gruppenDaten.abende.forEach { abend ->
                abend.abschnitte.forEach { abschnitt ->
                    htmlData.add(
                        listOf(
                            datum(abschnitt),
                            abschnitt.ort,
                            personen(abschnitt),
                            abschnitt.zusammenfassung.orEmpty(),
                            abenteuer(abschnitt, abend),
                            kapitel(abschnitt).orEmpty(),
                            abend.titel
                        )
                    )
                }
            }
            val daten = jsonMapper.writeValueAsString(htmlData)

            htmlFile.writeText(erstelleChronik(gruppenDaten.gruppe, daten))
        }
    }

    fun datum(abschnitt: Abschnitt) =
        if (aktuellesDatum != abschnitt.datum) {
            abschnitt.datum.also { aktuellesDatum = it }
        } else {
            aktuellesDatum
        }

    fun abenteuer(abschnitt: Abschnitt, abend: Abend) =
        if (abschnitt.abenteuer != null && aktuellesAbenteuer != abschnitt.abenteuer) {
            abschnitt.abenteuer.also { aktuellesAbenteuer = it }
        } else if (aktuellesAbenteuer != abend.abenteuer) {
            abend.abenteuer.also { aktuellesAbenteuer = it }
        } else {
            aktuellesAbenteuer
        }

    fun kapitel(abschnitt: Abschnitt) =
        if (abschnitt.kapitel != null && aktuellesKapitel != abschnitt.kapitel) {
            abschnitt.kapitel.also { aktuellesKapitel = it }
        } else {
            aktuellesKapitel
        }

    fun personen(abschnitt: Abschnitt) =
        (abschnitt.personen.orEmpty() + abschnitt.gruppen.orEmpty()).joinToString(transform = orEmpty)

    private val orEmpty: (String?) -> CharSequence = { it.orEmpty() }
}
