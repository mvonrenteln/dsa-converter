package com.github.mvonrenteln.dsa.converter

class Erlebnisse(gruppenName: String) : Abenteuer(gruppenName) {

    val abenteuer: MutableList<Abenteuer> = mutableListOf()

    fun abenteuerHinzufuegen(abenteuer: Abenteuer) {
        if (abenteuer != Abenteuer.LEER) {
            this.abenteuer.add(abenteuer)
            abende += abenteuer.abende
            spieldauer += abenteuer.spieldauer
            aps += abenteuer.aps
            charakterreife += abenteuer.charakterreife
        }
    }
}

open class Abenteuer(
    val name: String = "",
    var aps: Int = 0,
    var charakterreife: Int = 0,
    var abende: Int = 0,
    var spieldauer: Int = 0,
    val abendeDetails: MutableList<AbendAps> = mutableListOf()
) {

    fun abendHinzufuegen(abend: AbendAps) {
        if (abend.aps > 0) {
            abende++
            spieldauer += abend.spieldauer
            aps += abend.aps
            charakterreife += abend.charakterreife
            abendeDetails.add(abend)
        }
    }

    fun spieldauerDurchschnitt() = spieldauer / abende

    fun apsDurchschnitt() = aps / abende

    fun apsDurchschnittBeiDurchschnittlicherSpieldauer() = aps / spieldauer * spieldauerDurchschnitt()

    fun characterreifeDurchschnitt() = charakterreife / abende

    companion object {
        val LEER = Abenteuer()
    }
}

class AbendAps(
    val titel: String,
    val aps: Int,
    val charakterreife: Int,
    val spieldauer: Int,
    val zusammenfassung: String
)