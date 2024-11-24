package com.example.tapahtumattampere.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun formatDate(time: String): String {
    if (time == null) {
        return ""
    }
    val formattedDate = OffsetDateTime.parse(time)
    return formattedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}