package com.example.tapahtumattampere.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.data.MainViewModel


@Composable
fun EventList(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val events by viewModel.events
    if(events.isNotEmpty()){
        LazyColumn {
            items(events.size) { event ->
                EventListComponent(event = events[event], navController)
            }
        }
    }
    else{
        Text(text = "No events found")
    }
    LaunchedEffect(Unit) {
        viewModel.getEventsToday()
    }
}

