package com.example.tapahtumattampere.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private
    const val BASE_URL = "https://api.visittampere.com/"
    val api: EventApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(EventApiService::class.java)
    }
}
