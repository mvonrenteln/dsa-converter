package com.github.mvonrenteln.dsa.converter

import org.apache.velocity.app.Velocity
import java.util.*

fun initVelocity() =
    printMeasuredTimeAndReturnResult("Velocity initialisiert in {} ms.") {
        Velocity.init(Properties().apply {
            resourceAsStream("velocity.properties").use { this.load(it) }
        })
    }

val TEMPLATE by lazy { Velocity.getTemplate("template.html") }