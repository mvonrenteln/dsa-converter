package com.github.mvonrenteln.dsa.converter

import java.io.File
import java.io.Writer

class AdocFileWriter(adocFile: File) {

    val BR = "\r\n"
    val LEERZEILE = "$BR$BR"

    val writer: Writer

    var aktuellesAbenteuer = ""

    var aktuellesDatum: String? = null

    init {
        this.writer = adocFile.bufferedWriter()
    }

    fun writeData(gruppenDaten: GruppenDaten) {
        writer.use {
            writeDataInternal(gruppenDaten)
        }
    }

    private fun writeDataInternal(gruppenDaten: GruppenDaten) {
        writer.append("= ${gruppenDaten.titel} (${gruppenDaten.gruppe})$BR${gruppenDaten.verfasser}$BR:toc:$LEERZEILE")
        writer.append(gruppenDaten.einleitung + LEERZEILE)

        gruppenDaten.abende.forEach { abend ->

            if (aktuellesAbenteuer != abend.abenteuer) {
                aktuellesAbenteuer = abend.abenteuer
                schreibeAbenteuerName()
            }

            schreibeTitel(abend.titel)

            if (abend.zitat != null) {
                writer.append(abend.zitat + LEERZEILE)
            }

            abend.abschnitte.forEach { abschnitt ->
                if (abschnitt.abenteuer != null && aktuellesAbenteuer != abschnitt.abenteuer) {
                    aktuellesAbenteuer = abschnitt.abenteuer
                    schreibeAbenteuerName()
                    schreibeTitel(abend.titel)
                }
                if (aktuellesDatum != abschnitt.datum) {
                    aktuellesDatum = abschnitt.datum
                    writer.append("==== $aktuellesDatum$LEERZEILE")
                }

                writer.append(abschnitt.text + LEERZEILE)
            }
        }
    }

    private fun schreibeTitel(titel: String) {
        writer.append("=== $titel$LEERZEILE")
    }

    private fun schreibeAbenteuerName() {
        writer.append("== $aktuellesAbenteuer$LEERZEILE")
    }
}