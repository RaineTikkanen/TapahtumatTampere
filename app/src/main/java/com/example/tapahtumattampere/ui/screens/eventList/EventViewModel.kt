package com.example.tapahtumattampere.ui.screens.eventList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.domain.model.Event

import com.example.tapahtumattampere.repository.EventRepository
import kotlinx.coroutines.launch


sealed interface EventUiState {
    data class Success(val result: List<Event>) : EventUiState
    data object Error : EventUiState
    data object Loading : EventUiState
}

class EventViewModel(
    private val repository: EventRepository
): ViewModel() {
    var eventUiState: EventUiState by mutableStateOf(EventUiState.Loading)

    init{
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            eventUiState = EventUiState.Loading
            eventUiState = try {
                EventUiState.Success(repository.getAllEvents())
            } catch (e: Exception) {
                println("Error: $e")
                EventUiState.Error
            }
        }
    }
}