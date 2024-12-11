package com.example.tapahtumattampere.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoField

fun LocalDateTime.getWeekNumber(): Int {
    return get(ChronoField.ALIGNED_WEEK_OF_YEAR)
}