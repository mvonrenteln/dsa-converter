package com.github.mvonrenteln.dsa.converter

import org.apache.velocity.Template
import org.apache.velocity.app.Velocity
import java.util.*

fun initVelocity() =
    printMeasuredTimeAndReturnResult("Velocity initialisiert") {
        Velocity.init(Properties().apply {
            resourceAsStream("velocity.properties").use { this.load(it) }
        })
    }

val TEMPLATE: Template by lazy { Velocity.getTemplate("template.html.vm") }

val NSC_TEMPLATE: Template by lazy { Velocity.getTemplate("nsc-card.html.vm") }