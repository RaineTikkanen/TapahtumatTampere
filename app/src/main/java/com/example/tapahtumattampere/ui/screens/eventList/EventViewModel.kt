package com.example.tapahtumattampere.ui.screens.eventList

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
    data class Success(val result: List<Event>) : EventUiState
    data object Error : EventUiState
    data object Loading : EventUiState
}

class EventViewModel (private val eventsRepository: EventsRepository): ViewModel() {
    var eventUiState: EventUiState by mutableStateOf(EventUiState.Loading)

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

    private fun getEvents() {
        viewModelScope.launch {
            eventUiState = EventUiState.Loading
            eventUiState = try {
                EventUiState.Success(eventsRepository.getEvents())
            } catch (e: Exception) {
                EventUiState.Error
            }
        }
    }
}