package com.example.tapahtumattampere.utils

import java.time.LocalDateTime

fun formatDate(date: LocalDateTime): String{
    val day = date.dayOfMonth
    val month = date.monthValue
    val year = date.year
    return "$day.$month.$year"
}