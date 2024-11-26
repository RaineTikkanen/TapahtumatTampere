package com.example.tapahtumattampere.data

import com.example.tapahtumattampere.network.EventApiService

interface EventsRepository{
    suspend fun getEvents(): List<Event>
}

class NetworkEventsRepository(
    private val EventApiService: EventApiService
): EventsRepository{
    override suspend fun getEvents(): List<Event> {
        return EventApiService.getEvents()
    }
}