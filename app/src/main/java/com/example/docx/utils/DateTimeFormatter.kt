package com.example.docx.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toReadableTime(): String {
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    return formatter.format(Instant.ofEpochMilli(this))
}

fun Long.toPrettyDate(): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, d MMM")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    return formatter.format(Instant.ofEpochMilli(this))
}
