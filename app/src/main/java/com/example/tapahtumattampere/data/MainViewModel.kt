package com.example.tapahtumattampere.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api
    val events: MutableState < List < Event >> = mutableStateOf(emptyList())

    fun getEventsToday() {
        viewModelScope.launch {
            try {
                val response = apiService.getEventsToday()
                if (response.isNotEmpty()) {
                    events.value = response
                }else{
                    Log.e("MainViewModel", "No events found")

                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching events: ${e.message}")
            }
        }
    }
}