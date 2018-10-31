package com.github.mvonrenteln.dsa.converter

import kotlin.math.roundToInt

class Erlebnisse(gruppenName: String) : Abenteuer(gruppenName) {

    val abenteuer: MutableList<Abenteuer> = mutableListOf()

    fun abenteuerHinzufuegen(abenteuer: Abenteuer) {
        if (abenteuer != Abenteuer.LEER) {
            this.abenteuer.add(abenteuer)
            abende += abenteuer.abende
            spieldauer += abenteuer.spieldauer
            apsNachSystem.addiereWerte(abenteuer.apsNachSystem)
            charakterreifeNachSystem.addiereWerte(abenteuer.charakterreifeNachSystem)
        }
    }
}

open class Abenteuer(
    val name: String = "",
    var abende: Int = 0,
    var spieldauer: Int = 0,
    val abendeDetails: MutableList<AbendAps> = mutableListOf(),
    val apsNachSystem: MutableMap<RpgSystem, Int> = mutableMapOf(),
    val charakterreifeNachSystem: MutableMap<RpgSystem, Int> = mutableMapOf()
) {

    val aps: Int get() = getSummeUnterBerücksichtigungDesSystems(apsNachSystem)

    val charakterreife: Int get() = getSummeUnterBerücksichtigungDesSystems(charakterreifeNachSystem)

    private fun getSummeUnterBerücksichtigungDesSystems(nachSystem: Map<RpgSystem, Int>): Int {
        if (nachSystem.size == 1) {
            return nachSystem.values.first()
        } else if (nachSystem.keys.contains(RpgSystem.UNDEFINIERT)) {
            // Undefiniert lässt sich nicht sinnvoll konvertieren, also einfach alles aufsummieren
            return nachSystem.values.sum()
        } else {
            val dsa4Aps = nachSystem.getOrDefault(RpgSystem.DSA4, 0)
            val dsa5Aps = nachSystem.getOrDefault(RpgSystem.DSA5, 0)
            return dsa5Aps + dsa4Aps.div0Safe(8)
        }
    }

    fun abendHinzufuegen(abend: AbendAps) {
        if (abend.aps > 0) {
            abende++
            spieldauer += abend.spieldauer
            apsNachSystem.addiereWert(abend.system, abend.aps)
            charakterreifeNachSystem.addiereWert(abend.system, abend.charakterreife)
            abendeDetails.add(abend)
        }
    }

    fun spieldauerDurchschnitt() = (spieldauer.toDouble() / abende.toDouble()).roundToInt()

    fun apsDurchschnitt() = (aps.toDouble() / abende.toDouble()).roundToInt()

    fun characterreifeDurchschnitt() = (charakterreife.toDouble() / abende.toDouble()).roundToInt()

    companion object {
        val LEER = Abenteuer()
    }
}


class AbendAps(
    val titel: String,
    val aps: Int,
    val charakterreife: Int,
    val spieldauer: Int,
    val zusammenfassung: String,
    val system: RpgSystem
)