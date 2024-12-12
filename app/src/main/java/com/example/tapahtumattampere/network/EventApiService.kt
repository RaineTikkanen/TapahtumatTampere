package com.example.tapahtumattampere.network


import com.example.tapahtumattampere.network.model.EventDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface EventApiService{
    @GET("api/v1/eventztoday/event/all/?format=json")
    suspend fun getEvents(
        @Query("lang") lang: String
    ): List<EventDTO>
}