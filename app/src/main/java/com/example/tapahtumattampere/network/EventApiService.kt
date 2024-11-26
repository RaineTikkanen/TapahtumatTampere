package com.example.tapahtumattampere.network

import com.example.tapahtumattampere.data.Event
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface EventApiService{
    @GET("api/v1/eventztoday/event/all/?format=json&lang=fi")
    suspend fun getEvents(): List<Event>
}