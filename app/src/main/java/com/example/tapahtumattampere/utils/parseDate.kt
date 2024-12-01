package com.example.tapahtumattampere.utils

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun parseDate(dateString: String): LocalDateTime {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val zonedDateTime = ZonedDateTime.parse(dateString, formatter)
    return zonedDateTime.toLocalDateTime()
}