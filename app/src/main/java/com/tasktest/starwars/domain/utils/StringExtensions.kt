package com.tasktest.starwars.domain.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun List<String>.takeOutIds(): String {
    return this.first().takeOutId().toString()
}

fun String.takeOutId(): Int {
    return this.toCharArray().let {
        it[it.size - 2].toString()
    }.toInt()
}

fun String.toFormattedDate(): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(this.trim(), formatter)
        "${date.month} ${date.dayOfMonth},${date.year}"
    } catch (e: Exception) {
        return this
    }
}