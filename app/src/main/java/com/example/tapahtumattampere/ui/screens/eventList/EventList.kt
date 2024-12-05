package com.example.tapahtumattampere.ui.screens.eventList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen


@Composable
fun EventList(
    navController: NavController,
    eventUiState: EventUiState,
    ) {
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Error -> ErrorScreen()
        is EventUiState.Success -> {
            val events = eventUiState.result
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalItemSpacing = 5.dp,
            ) {
                items(events.size, key = { events[it].id }) { event ->
                    EventListComponent(event = events[event], onEventClick = {
                        navController.navigate(Info(it))
                    })
                }
            }
        }
    }
}