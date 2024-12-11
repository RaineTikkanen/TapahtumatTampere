package com.example.tapahtumattampere.ui.screens.eventInfoScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.domain.model.EventDate
import com.example.tapahtumattampere.network.RetrofitInstance
import com.example.tapahtumattampere.network.model.EventDTOMapper
import com.example.tapahtumattampere.utils.findNextDates

import kotlinx.coroutines.launch


interface EventInfoViewModelState {
    object Loading : EventInfoViewModelState
    object Error : EventInfoViewModelState
    object Success : EventInfoViewModelState
}

class EventInfoViewModel(id: String, lang: String) : ViewModel() {
    var event by mutableStateOf<Event?>(null)
    private val api = RetrofitInstance.api
    private val mapper = EventDTOMapper()
    var nextDates by mutableStateOf<List<EventDate>>(emptyList())
    var state by mutableStateOf<EventInfoViewModelState>(EventInfoViewModelState.Loading)

    init {
        getEvent(id, lang)
    }

    private fun getEvent(id: String, lang:String) {
        viewModelScope.launch {
            try {
                val result = api.getEvents(lang=lang).find { it.id2 == id }
                if (result == null) {
                    throw Exception("Event not found")
                }
                event = mapper.toDomainModel(result)
                nextDates = findNextDates(event!!.dates)
                state = EventInfoViewModelState.Success
            } catch (e: Exception) {
                e.printStackTrace()
                state = EventInfoViewModelState.Error
            }
        }
    }

    fun openUrl(context: Context) {
        if (event != null) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event!!.url))
            startActivity(context, intent, null)
        }
    }

    fun openMaps(context: Context) {
        if (event != null&&event?.location!=null) {
            val url = Uri.parse("https://www.google.com/maps/search/?api=1&query=${event!!.location!!.lat},${event!!.location!!.lon}")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(context, intent, null)
        }
    }
}