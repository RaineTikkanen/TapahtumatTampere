package com.example.tapahtumattampere.utils

import com.example.tapahtumattampere.network.model.Date
import java.time.LocalDate
import java.time.LocalDateTime

/*
fun findNextDates(dates: List<Date>): List<LocalDateTime> {
    val today = LocalDate.now()
    return dates.mapNotNull { date ->
        val parsedDate = parseDate(date.start)
        if (parsedDate.toLocalDate().isAfter(today)||parsedDate.toLocalDate().isEqual(today)) {
            parsedDate
        } else null
    }.sorted()
}*/