package com.example.tapahtumattampere.data

data class Event(
    val id: String,
    val _id: String,
    val creationDate: String,
    val modificationDate: String,
    val name: String,
    val start_time: String,
    val end_time: String,
    val locations: List<Location>,
    val dates: List<Date>,
    val links: List<Link>,
    val sourceName: String,
    val description: String,
    val price: Price,
    val images: List<Image>,
    val imageCopyrights: String?,
    val imageAlt: String?,
    val language: String,
    val siteName: String,
    val categories: List<String>,
    val topics: List<String>,
    val targets: List<String>,
    val tags: List<String>,
    val url: String,
    val areas: List<Area>
)

data class Location(
    val address: String,
    val pageId: String,
    val geoIndex: List<Double>,
    val locationId: String
)

data class Date(
    val end: String,
    val start: String,
    val isSoldOut: Boolean
)

data class Link(
    val url: String,
    val name: String
)

data class Price(
    val max: Float,
    val min: Float,
    val isFree: Boolean
)

data class Image(
    val url: String,
    val type: String,
    val width: Int,
    val height: Int
)

data class Area(
    val name: String
)