package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import com.example.tapahtumattampere.utils.findNextDates
import com.example.tapahtumattampere.utils.getWeekNumber
import kotlinx.coroutines.launch
import java.time.LocalDateTime


interface HomeScreenViewModelState {
    object Loading : HomeScreenViewModelState
    object Error : HomeScreenViewModelState
    object Success : HomeScreenViewModelState
}

class HomeScreenViewModel(lang: String): ViewModel() {
    private var _events by mutableStateOf<List<Event>>(listOf())
    var eventsToday by mutableStateOf<List<Event>>(listOf())
    var eventsThisWeek by mutableStateOf<List<Event>>(listOf())
    var eventsThisMonth by mutableStateOf<List<Event>>(listOf())
    private val _now = LocalDateTime.now()
    private val thisWeek = _now.getWeekNumber()
    private val _api= RetrofitInstance.api
    private val _mapper = EventDTOMapper()
    var state by mutableStateOf<HomeScreenViewModelState>(HomeScreenViewModelState.Loading)

    init{
        getEvents(lang)
    }


    private fun filterToday(){
        eventsToday = _events.filter { event ->
            findNextDates(event.dates).any { date ->
                date.start.toLocalDate() == _now.toLocalDate()
            }
        }
    }
    private fun filterThisWeek(){
        eventsThisWeek = _events.filter { event ->
            findNextDates(event.dates).any { date ->
                date.start.getWeekNumber() == thisWeek && date.start.toLocalDate() != _now.toLocalDate()
            }
        }
    }

    private fun filterThisMonth() {
        eventsThisMonth = _events.filter { event ->
            findNextDates(event.dates).any { date ->
                date.start.month == _now.month && date.start.getWeekNumber()!=thisWeek
            }
        }
    }
    private fun getEvents(lang: String) {
        viewModelScope.launch {
            try {
                val result = _api.getEvents(lang = lang)
                _events = _mapper.toDomainList(result)
                filterToday()
                filterThisWeek()
                filterThisMonth()
                state = HomeScreenViewModelState.Success
            } catch (e: Exception) {
                e.printStackTrace()
                state = HomeScreenViewModelState.Error
            }
        }
    }
}