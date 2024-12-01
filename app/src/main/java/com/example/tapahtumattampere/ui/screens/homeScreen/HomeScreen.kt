package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.eventList.EventUiState
import com.example.tapahtumattampere.ui.screens.LoadingScreen
import com.example.tapahtumattampere.utils.getWeekNumber
import com.example.tapahtumattampere.utils.parseDate
import java.time.LocalDate

@Composable
fun HomeScreen(headerViewModel: HeaderViewModel, navController: NavController, eventUiState: EventUiState){
    headerViewModel.updateHeaderText("Tapahtumat")
    when (eventUiState) {
        is EventUiState.Loading -> LoadingScreen()
        is EventUiState.Success -> ResultScreen(eventUiState.result, navController)
        is EventUiState.Error -> ErrorScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(events: List<Event>, navController: NavController) {
    val today = LocalDate.now()
    val thisWeek = today.getWeekNumber()
    val eventsToday = remember {
        events.filter { event ->
            event.dates.any { date ->
                parseDate(date.start).toLocalDate() == today
            }
        }
    }
    val eventsThisWeek = remember {
        events.filter { event ->
            event.dates.any { date ->
                parseDate(date.start).toLocalDate().getWeekNumber() == thisWeek
            }
        }
    }
    val eventsThisMonth = remember {
        events.filter { event ->
            event.dates.any { date ->
                parseDate(date.start).month == today.month && parseDate(date.start).year == today.year
            }
        }
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp)
    ){
        Text(text = "Tänään:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        HomeScreenCarousel(navController, eventsToday, Time.TIME)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text(text = "Tällä viikolla:",
            style = MaterialTheme.typography.titleLarge
        )
        HomeScreenCarousel(navController, eventsThisWeek, Time.DAY)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text(text = "Tässä kuussa:",
            style = MaterialTheme.typography.titleLarge
        )
        HomeScreenCarousel(navController, eventsThisMonth, Time.DATE)
    }
}

enum class Time
{
    TIME,
    DAY,
    DATE

}