package com.example.tapahtumattampere.di

import com.example.tapahtumattampere.network.EventApiService
import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideEventService(): RetrofitInstance {
        return RetrofitInstance
    }

    @Provides
    @Singleton
    fun provideEventDtoMapper(): EventDTOMapper {
        return EventDTOMapper()
    }
}