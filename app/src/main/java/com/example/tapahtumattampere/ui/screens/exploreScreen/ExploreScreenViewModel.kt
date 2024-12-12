package com.example.tapahtumattampere.ui.screens.exploreScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


interface ExploreScreenViewModelState {
    object Loading : ExploreScreenViewModelState
    object Error : ExploreScreenViewModelState
    object Success : ExploreScreenViewModelState
}

class ExploreScreenViewModel(lang: String): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _searchBarVisible = MutableStateFlow(false)
    val searchBarVisible = _searchBarVisible.asStateFlow()

    private val _events = MutableStateFlow<List<Event>>(listOf())
    val events = searchText
        .debounce(500)
        .combine(_events) { text, events ->
        if(text.isBlank()) {
            events
        } else {

            events.filter {
                it.doesMatchQuery(text)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _events.value
    )
    private val _api= RetrofitInstance.api
    private val _mapper = EventDTOMapper()
    var state by mutableStateOf<ExploreScreenViewModelState>(ExploreScreenViewModelState.Loading)
        private set


    init {
        getEvents(lang)
    }

    private fun getEvents(lang: String) {
        viewModelScope.launch {
            try {
                val result = _api.getEvents(lang = lang)
                //Filter out events with HTML formatting in description
                _events.value = _mapper.toDomainList(result).filter { event ->
                    !event.description.contains("<p>")
                }
                state = ExploreScreenViewModelState.Success
            } catch (e: Exception) {
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun toggleSearchBarVisibility() {
        if (_searchBarVisible.value) {
            _searchText.value = ""
            _searchBarVisible.value = false
        } else {
            _searchBarVisible.value = true
        }
    }
}


