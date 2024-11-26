package com.example.tapahtumattampere.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tapahtumattampere.EventsApplication
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.data.EventsRepository
import kotlinx.coroutines.launch


sealed interface EventUiState {
    data class Success(val result: String) : EventUiState
    object Error : EventUiState
    object Loading : EventUiState
}

class EventViewModel (private val eventsRepository: EventsRepository): ViewModel() {
    var eventUiState: EventUiState by mutableStateOf(EventUiState.Loading)
    var events: List<Event> by mutableStateOf(listOf())

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EventsApplication)
                val eventsRepository = application.container.eventsRepository
                EventViewModel(eventsRepository = eventsRepository)
            }
        }
    }

    init{
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            eventUiState = EventUiState.Loading
            eventUiState = try {
                events = eventsRepository.getEvents()
                EventUiState.Success(result = "Success: ${events.size} events found")
            } catch (e: Exception) {
                EventUiState.Error
            }
        }
    }
}