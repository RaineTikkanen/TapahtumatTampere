package com.example.tapahtumattampere.domain.model

import java.time.LocalDateTime

data class Event (
    val id: String,
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val location: Coordinates?,
    val address: Address?,
    val dates: List<EventDate>,
    val url: String,
    val image: String,
    val categories: List<String>,
){
    fun doesMatchQuery(query: String): Boolean {
        val matchResult = listOf(
            name,
            description,
            categories.joinToString(" "),
            )
        return matchResult.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


data class Coordinates(
    val lon: Double,
    val lat: Double
)
data class Address(
    val name: String,
    val address: String?= null,
    val zipCode: String?= null,
    val city: String?= null
)

data class EventDate(
    val start: LocalDateTime,
    val end: LocalDateTime,
)