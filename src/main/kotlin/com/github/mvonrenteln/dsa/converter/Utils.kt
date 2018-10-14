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