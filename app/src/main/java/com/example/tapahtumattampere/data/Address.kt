package com.example.tapahtumattampere.data

data class Address(
    val name: String,
    val address: String?= null,
    val zipCode: String?= null,
    val city: String?= null
)