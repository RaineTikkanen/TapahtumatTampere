package com.example.tapahtumattampere.repository

import com.example.tapahtumattampere.domain.model.Event

interface EventRepository {
    suspend fun getEvents(): List<Event>

    suspend fun getEventById(id: String): Event
}