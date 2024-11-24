package com.example.tapahtumattampere.data

import retrofit2.http.GET


interface ApiService{
    @GET("api/v1/eventztoday/event/all/?format=json&lang=fi")
    suspend fun getEventsToday(): List<Event>
}

