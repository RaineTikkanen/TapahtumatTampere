package com.example.tapahtumattampere.utils

import com.example.tapahtumattampere.domain.model.EventDate
import java.time.LocalDate


fun findNextDates(dates: List<EventDate>): List<EventDate> {
    val today = LocalDate.now()
    return dates.mapNotNull { date ->
        if (date.start.toLocalDate().isAfter(today)||date.start.toLocalDate().isEqual(today)) {
            date
        } else null
    }.sortedBy { it.start }
}