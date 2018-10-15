package com.github.mvonrenteln.dsa.converter

import kotlin.system.measureTimeMillis

fun <T> printMeasuredTimeAndReturnResult(message: String, block: () -> T): T {
    var returnValue: T? = null
    val time = measureTimeMillis {
        returnValue = block()
    }
    println(message.format(time))
    return returnValue!!
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