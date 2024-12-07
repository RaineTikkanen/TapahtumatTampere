package com.example.tapahtumattampere.di

import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import com.example.tapahtumattampere.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideEventRepository(
        apiService: RetrofitInstance,
        mapper: EventDTOMapper
    ): EventRepository{
        return EventRepository(apiService, mapper)
    }
}
