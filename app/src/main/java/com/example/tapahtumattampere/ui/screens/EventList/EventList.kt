package com.example.tapahtumattampere.ui.screens.EventList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen


fun filter(events: List<Event>, viewOption: ViewOptions): List<Event> {
    return(
            when (viewOption) {
                ViewOptions.SPORT -> {
                    events.filter { it.categories.contains("Urheilu") }
                }

                ViewOptions.MUSEUM-> {
                    events.filter { it.categories.contains("Museot ja galleriat") }
                }

                ViewOptions.THEATRE -> {
                    events.filter { it.categories.contains("Teatteri") }
                }

                else -> {
                    events
                }
            }
            )

}

@Composable
fun EventList(navController: NavController,eventUiState: EventUiState, viewOption: ViewOptions) {
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Success -> ResultScreen2(filter(eventUiState.result, viewOption), navController)
        is EventUiState.Error -> ErrorScreen()
    }
}

@Composable
fun ResultScreen(events: List<Event>, navController: NavController) {
    LazyColumn (){
        items(events.size) { event ->
            EventListComponent(event = events[event], navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen2(events: List<Event>, navController: NavController) {

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
