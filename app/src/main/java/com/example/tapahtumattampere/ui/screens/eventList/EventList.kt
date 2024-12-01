package com.example.tapahtumattampere.ui.screens.eventList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen


fun filter(events: List<Event>, filter: FILTER): List<Event> {
    return(
            when (filter) {
                FILTER.SPORT -> {
                    events.filter { it.categories.contains("Urheilu") }
                }
                FILTER.MUSEUM-> {
                    events.filter { it.categories.contains("Museot ja galleriat") }
                }
                FILTER.THEATRE -> {
                    events.filter { it.categories.contains("Teatteri") }
                }
                FILTER.MUSIC -> {
                    events.filter { it.categories.contains("Musiikki") }
                }
                FILTER.KIDS -> {
                    events.filter { it.categories.contains("Lapset") }
                }
                else -> {
                    events
                }
            }
            )

}

@Composable
fun EventList(navController: NavController,eventUiState: EventUiState, filter: FILTER) {
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Success -> {
            val events = remember { filter(eventUiState.result, filter) }
            ResultScreen(events, navController)
        }
        is EventUiState.Error -> ErrorScreen()
    }
}



@Composable
private fun ResultScreen(events: List<Event>, navController: NavController) {
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
