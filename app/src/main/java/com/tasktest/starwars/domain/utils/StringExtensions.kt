package com.tasktest.starwars.domain.utils

fun List<String>.takeOutIds(): String {
    return this.first().takeOutId().toString()
}

fun String.takeOutId(): Int {
    return this.toCharArray().let {
        it[it.size - 2].toString()
    }.toInt()
}