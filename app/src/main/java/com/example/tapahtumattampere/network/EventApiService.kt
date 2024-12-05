package com.example.tapahtumattampere.network


import com.example.tapahtumattampere.network.model.EventDTO
import retrofit2.http.GET

interface EventApiService{
    @GET("api/v1/eventztoday/event/all/?format=json&lang=fi")
    suspend fun getEvents(): List<EventDTO>
}