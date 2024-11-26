package com.example.tapahtumattampere

import android.app.Application
import com.example.tapahtumattampere.data.AppContainer
import com.example.tapahtumattampere.data.DefaultAppContainer

class EventsApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}