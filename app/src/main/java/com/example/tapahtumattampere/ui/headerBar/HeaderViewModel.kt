package com.example.tapahtumattampere.ui.headerBar


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeaderViewModel : ViewModel() {
    private val _headerText = MutableLiveData<String>()
    val headerText: LiveData<String> = _headerText
    fun updateHeaderText(text: String) {
        _headerText.value = text
    }
}