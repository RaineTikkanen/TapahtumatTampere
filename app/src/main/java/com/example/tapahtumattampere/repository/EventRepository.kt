package com.example.tapahtumattampere.repository

import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper

class EventRepository(
    private val apiService: RetrofitInstance,
    private val mapper: EventDTOMapper
) {
    suspend fun getAllEvents(): List<Event> {
        val events = apiService.api.getAllEvents()
        return mapper.toDomainList(events)
    }

    suspend fun getEventById(id: String): Event{
        val event = apiService.api.getEventById(id)
        return mapper.toDomainModel(event)
    }

}