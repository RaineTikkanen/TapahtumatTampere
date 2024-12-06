package com.example.tapahtumattampere.network


import com.example.tapahtumattampere.network.model.EventDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApiService{
    @GET("api/v1/eventztoday/event/all/?format=json&lang=fi")
    suspend fun getAllEvents(): List<EventDTO>
    @GET("api/v1/eventztoday/event/{id}/?format=json&lang=fi")
    suspend fun getEventById(@Path("id") id: String): EventDTO
}