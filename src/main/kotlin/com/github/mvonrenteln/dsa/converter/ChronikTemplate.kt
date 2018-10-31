package com.github.mvonrenteln.dsa.converter

import org.apache.velocity.VelocityContext
import java.io.StringWriter

fun erstelleChronik(gruppenDaten: GruppenDaten, chronikDaten: String): String {
    val context = VelocityContext().apply {
        put("containerTyp", "container")
        put("sidebar", false)
        put("gruppenDaten", gruppenDaten)
        put("chronikDaten", chronikDaten)
    }
    val chronikBody = StringWriter().apply { CHRONIK_TEMPLATE.merge(context, this) }.toString()

    context.put("body", chronikBody)
    return StringWriter().apply { TEMPLATE.merge(context, this) }.toString()
}