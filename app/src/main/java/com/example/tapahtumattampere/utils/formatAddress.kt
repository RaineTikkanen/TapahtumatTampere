package com.example.tapahtumattampere.utils

import com.example.tapahtumattampere.data.Address

fun formatAddress(addressText: String): Address {
    val parts = addressText.split(", ")
    return when (parts.size) {
        1 -> Address(parts[0])
        2 -> Address(parts[0], parts[1])
        3 -> Address(parts[0], parts[1], parts[2])
        else -> Address(parts[0], parts[1], parts[2], parts[3])
    }
}