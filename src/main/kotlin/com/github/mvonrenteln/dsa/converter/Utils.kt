package com.github.mvonrenteln.dsa.converter

import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis


val logger = LoggerFactory.getLogger("dsa-converter")

var sumTime = 0L

inline fun <T> printMeasuredTimeAndReturnResult(aktion: String, block: () -> T): T {
    var returnValue: T? = null
    val time = measureTimeMillis {
        returnValue = block()
    }
    val stringLength = 69 - aktion.length
    logger.debug(aktion + "$time ms".padStart(stringLength))
    sumTime += time
    return returnValue!!
}

fun printSumTime() {
    val aktion = "Summe der Einzelschritte ohne Corotines wären mindestens"

    logger.debug(aktion + "[$sumTime ms]".padStart(70 - aktion.length))
}

fun String.removeRange(start: String, ende: String): String {
    val startIndex = this.indexOf(start)
    val endeIndex = this.indexOf(ende)
    return if (startIndex != -1 && endeIndex != -1) this.removeRange(startIndex, endeIndex + ende.length) else this
}

fun String.entferneAllesAb(start: String): String {
    val startIndex = this.indexOf(start)
    return if (startIndex != -1) this.substring(0, startIndex) else this
}

fun String.onlyWordCharacters(): String {
    val output = StringBuilder(this.length)

    for (ch in this) {
        if (Character.isLetterOrDigit(ch)) {
            output.append(ch)
        } else if (Character.isWhitespace(ch)) {
            output.append('-')
        }
    }

    return output.toString()
}

fun <T> MutableMap<T, Int>.addiereWerte(valuesToAdd: Map<T, Int>) =
    valuesToAdd.forEach { this.addiereWert(it.key, it.value) }


fun <T> MutableMap<T, Int>.addiereWert(key: T, amountToAdd: Int) {
    val newValue = this.getOrDefault(key, 0) + amountToAdd
    this[key] = newValue
}


/**
 * "0"-sichere Division
 */
fun Int.div0Safe(other: Int) = if (this == 0) 0 else this / other