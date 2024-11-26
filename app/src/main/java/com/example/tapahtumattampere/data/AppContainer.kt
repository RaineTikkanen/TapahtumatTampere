package com.example.tapahtumattampere.data

import com.example.tapahtumattampere.network.EventApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val eventsRepository: EventsRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://api.visittampere.com/"

    private  val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }

    override val eventsRepository: EventsRepository by lazy {
        NetworkEventsRepository(retrofitService)
    }

}