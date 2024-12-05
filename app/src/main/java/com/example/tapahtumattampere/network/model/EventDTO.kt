package com.example.tapahtumattampere.network.model

import com.google.gson.annotations.SerializedName


data class EventDTO(
    val id: String,
    @SerializedName("_id")
    val id2: String,
    val creationDate: String,
    val modificationDate: String,
    val name: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    val locations: List<Location> ?= null,
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