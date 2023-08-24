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
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        val date = LocalDate.parse(this.trim(), inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        return this
    }
}