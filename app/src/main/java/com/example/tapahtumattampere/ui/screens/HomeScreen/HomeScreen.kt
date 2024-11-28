package com.example.tapahtumattampere.ui.screens.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.EventList.EventListComponent
import com.example.tapahtumattampere.ui.screens.EventList.EventUiState
import com.example.tapahtumattampere.ui.screens.LoadingScreen
import com.example.tapahtumattampere.utils.formatDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(headerViewModel: HeaderViewModel, navController: NavController, eventUiState: EventUiState){
    headerViewModel.updateHeaderText("Tapahtumat")
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Success -> ResultScreen(eventUiState.result, navController)
        is EventUiState.Error -> ErrorScreen()
    }
}

fun parseDateString(dateString: String): LocalDate {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val zonedDateTime = ZonedDateTime.parse(dateString, formatter)
    return zonedDateTime.toLocalDate()
}

@Composable
fun ResultScreen(events: List<Event>, navController: NavController) {
    val today = LocalDate.now()
    val eventsToday = events.filter { event ->
        event.dates.any { date ->
            parseDateString(date.start) == today
        }
    }
    Column(){
        Text(text = "Tänään tapahtuu:",
            modifier = Modifier.padding(16.dp))
        LazyRow() {
            items(eventsToday.size) { item ->
                HomeScreenComponent(event = eventsToday[item], navController)
            }

        }
    }
}