package com.github.mvonrenteln.dsa.converter.legacy

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class LegacyGruppenDaten(
    val gruppe: String,
    val abende: List<LegacyAbend>
)


@JsonIgnoreProperties(value = ["subject"])
data class LegacyAbend(
    val abenteuer: String,
    val datum: String,
    val titel: String,
    val aps: List<AP>,
    val zitat: String?,
    val daten: String?,
    val text: String
)

data class AP(val aps: Int, val beschreibung: String)

