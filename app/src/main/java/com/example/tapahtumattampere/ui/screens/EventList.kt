package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun EventList(navController: NavController,eventViewModel: EventViewModel) {
    val eventUiState = eventViewModel.eventUiState
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Success -> ResultScreen2(eventViewModel, navController)
        is EventUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Loading")
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Can't load data")
    }
}

@Composable
fun ResultScreen(eventViewModel: EventViewModel, navController: NavController) {
    val events = eventViewModel.events
    LazyColumn {
        items(events.size) { event ->
            EventListComponent(event = events[event], navController)
        }
    }
}

@Composable
fun ResultScreen2(eventViewModel: EventViewModel, navController: NavController) {
    val events = eventViewModel.events
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalItemSpacing = 5.dp,
    ) {
        items(events.size) { event ->
            EventListComponent(event = events[event], navController)
        }
    }
}
