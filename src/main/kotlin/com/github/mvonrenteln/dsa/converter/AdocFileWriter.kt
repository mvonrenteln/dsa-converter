package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.FileOutputStream
import java.io.Writer

class AdocFileWriter(val adocFile: File) {

    val BR = "\r\n"
    val LEERZEILE = "$BR$BR"

    var aktuellesAbenteuer = ""

    var aktuellesDatum: String? = null

    init {
        adocFile.delete()
    }

    fun writeData(gruppenDaten: GruppenDaten) {
        FileOutputStream(adocFile, true).writer().buffered().use {
            writeDataInternal(gruppenDaten, it)
        }
    }

    private fun writeDataInternal(gruppenDaten: GruppenDaten, writer: Writer) {
        writer.append("= ${gruppenDaten.titel} (${gruppenDaten.gruppe})$BR${gruppenDaten.verfasser}$BR:toc:$LEERZEILE")
        writer.append(gruppenDaten.einleitung + LEERZEILE)

        gruppenDaten.abende.forEach { abend ->

            if (aktuellesAbenteuer != abend.abenteuer) {
                aktuellesAbenteuer = abend.abenteuer
                schreibeAbenteuerName(writer)
            }

            schreibeTitel(abend.titel, writer)

            if (abend.zitat != null) {
                writer.append(abend.zitat + LEERZEILE)
            }

            abend.abschnitte.forEach { abschnitt ->
                if (abschnitt.abenteuer != null && aktuellesAbenteuer != abschnitt.abenteuer) {
                    aktuellesAbenteuer = abschnitt.abenteuer
                    schreibeAbenteuerName(writer)
                    schreibeTitel(abend.titel, writer)
                }
                if (aktuellesDatum != abschnitt.datum) {
                    aktuellesDatum = abschnitt.datum
                    writer.append("==== $aktuellesDatum$LEERZEILE")
                }

                writer.append(abschnitt.text + LEERZEILE)
            }
        }
    }

    private fun schreibeTitel(titel: String, writer: Writer) {
        writer.append("=== $titel$LEERZEILE")
    }

    private fun schreibeAbenteuerName(writer: Writer) {
        writer.append("== $aktuellesAbenteuer$LEERZEILE")
    }
}