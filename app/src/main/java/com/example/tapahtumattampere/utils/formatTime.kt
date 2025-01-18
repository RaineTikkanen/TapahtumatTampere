package com.example.tapahtumattampere.utils

import java.time.LocalDateTime

fun formatTime(time: LocalDateTime): String{
    val hour = time.hour
    val minute = time.minute
    val formattedHour = if (hour < 10) "0$hour" else hour.toString()
    val formattedMinute = if (minute < 10) "0$minute" else minute.toString()
    return "$formattedHour:$formattedMinute"
}