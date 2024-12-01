package com.example.tapahtumattampere.utils

import java.time.LocalDate
import java.time.temporal.ChronoField

fun LocalDate.getWeekNumber(): Int {
    return get(ChronoField.ALIGNED_WEEK_OF_YEAR)
}