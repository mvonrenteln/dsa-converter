package com.github.mvonrenteln.dsa.converter

data class GruppenDaten(
    val gruppe: String,
    val mitglieder: List<Held>? = null,
    val titel: String? = null,
    val verfasser: String? = null,
    val einleitung: String? = null,
    val abende: List<Abend>
)

data class Held(val name: String, val profession: String)

data class Abend(
    val kampagne: String? = null,
    val abenteuer: String,
    val datum: String,
    val spieldauer: Int,
    val titel: String,
    val abschnitte: List<Abschnitt>,
    val aps: List<AP>,
    val zitat: String?,
    val daten: String?
)

data class AP(val aps: Int, val beschreibung: String)

data class Abschnitt(
    val datum: String,
    val kapitel: String? = null,
    val ort: String,
    val abenteuer: String? = null,
    val gruppen: List<String>? = null,
    val ausrüstung: List<String>? = null,
    val personen: List<String>? = null,
    val text: String,
    val zusammenfassung: String? = null,
    val effekte: List<Effekt>? = null
)

data class Effekt(val auswirkung: String, val held: String, val wirktBis: String)

data class Nsc(
    val vorname: String?,
    val name: String?,
    val titel: String?,
    val profession: String?,
    val spezies: String?,
    val geschlecht: String?,
    val gruppen: List<String>?,
    val wohnort: String?,
    val position: String?,
    val aussehen: String?,
    val alter: String?,
    val tsatag: String?,
    val sprache: String?,
    val charakter: String?,
    val zitate: List<String>?,
    val beziehungZuHelden: String?,
    val motivation: String?,
    val vergangenheit: String?,
    val status: String?,
    val tags: List<String>?,
    val wikiLink: String?,
    val bild: String?
) {
    fun berechneAlter() = alter ?: 1018-tsatag!!.substringBefore("BF").trim().toInt()

    fun berechneTsatag() = tsatag ?: (1018 - alter!!.toInt()).toString()+" BF"

    val id = (vorname + "-" + name).replace(' ', '-')
}

