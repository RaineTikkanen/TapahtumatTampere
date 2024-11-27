package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.data.EventsRepository
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.EventList.EventUiState

@Composable
fun EventInfo(headerViewModel: HeaderViewModel, id: String,eventUiState: EventUiState) {
    val event = (eventUiState as EventUiState.Success).result.find { it.id == id }
    if (event != null) {
        headerViewModel.updateHeaderText(event.name)
        Column {
            AsyncImage(
                model = event.images[0].url,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = event.description)
        }
    } else {
        Text(text = "Event not found")

    }
}