package com.example.tapahtumattampere.ui.screens.eventList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.domain.model.Event

import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import kotlinx.coroutines.launch


sealed interface EventUiState {
    data class Success(val result: List<Event>) : EventUiState
    data object Error : EventUiState
    data object Loading : EventUiState
}

class EventViewModel (): ViewModel() {
    private val _apiService = RetrofitInstance.api
    var eventUiState: EventUiState by mutableStateOf(EventUiState.Loading)


    init{
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            eventUiState = EventUiState.Loading
            eventUiState = try {
                val events = _apiService.getEvents()
                val mappedEvents = EventDTOMapper().fromEntityList(events)
                EventUiState.Success(mappedEvents)
            } catch (e: Exception) {
                println("Error: $e")
                EventUiState.Error
            }
        }
    }
}