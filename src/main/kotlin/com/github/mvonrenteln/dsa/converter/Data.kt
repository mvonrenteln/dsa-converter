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

