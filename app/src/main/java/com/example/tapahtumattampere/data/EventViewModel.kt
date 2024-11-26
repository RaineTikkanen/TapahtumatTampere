package com.example.tapahtumattampere.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.network.EventApi
import kotlinx.coroutines.launch


sealed interface EventUiState {
    data class Success(val events: String) : EventUiState
    object Error : EventUiState
    object Loading : EventUiState
}

class EventViewModel : ViewModel() {
    var eventUiState: EventUiState by mutableStateOf(EventUiState.Loading)

    init{
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            eventUiState = EventUiState.Loading
            eventUiState = try {
                val eventsRepository = NetworkEventsRepository()
                val events = eventsRepository.getEvents()
                EventUiState.Success("Success: ${events.size} events found")
            } catch (e: Exception) {
                EventUiState.Error
            }
        }
    }
}