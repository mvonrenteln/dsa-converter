package com.github.mvonrenteln.dsa.converter

data class GruppenDaten(
    val gruppe: String,
    val mitglieder: List<Held>,
    val titel: String,
    val verfasser: String,
    val einleitung: String?,
    val abende: List<Abend>
)

data class Held(val name: String, val profession: String)

data class Abend(
    val kampagne: String,
    val abenteuer: String,
    val datum: String,
    val spieldauer: Int,
    val titel: String,
    val abschnitte: List<Abschnitt>,
    val aps: List<AP>,
    val zitat: String?
)

data class AP(val aps: Int, val beschreibung: String)

data class Abschnitt(
    val datum: String,
    val kapitel: String?,
    val ort: String,
    val abenteuer: String?,
    val gruppen: List<String>?,
    val ausrüstung: List<String>?,
    val personen: List<String>?,
    val text: String,
    val zusammenfassung: String?
)

