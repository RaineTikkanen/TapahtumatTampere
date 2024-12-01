package com.example.tapahtumattampere.utils

import java.time.DayOfWeek
import java.time.LocalDateTime

fun formatDay(date: LocalDateTime): String {
    val day = date.dayOfWeek
    when (day) {
        DayOfWeek.MONDAY -> return "Maanantai"
        DayOfWeek.TUESDAY -> return "Tiistai"
        DayOfWeek.WEDNESDAY -> return "Keskiviikko"
        DayOfWeek.THURSDAY -> return "Torstai"
        DayOfWeek.FRIDAY -> return "Perjantai"
        DayOfWeek.SATURDAY -> return "Lauantai"
        DayOfWeek.SUNDAY -> return "Sunnuntai"
    }
}