package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.eventList.EventUiState
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime

@Composable
fun EventInfo(headerViewModel: HeaderViewModel, id: String,eventUiState: EventUiState) {
    val event = (eventUiState as EventUiState.Success).result.find { it.id == id }
    //val nextDates = findNextDates(event!!.dates)
    headerViewModel.updateHeaderText("Info")
    if (event != null) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = event.image,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(16.dp))
                if (event.address != null) {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = event.address.name,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                if (event.dates.isNotEmpty()) {
                    Text(
                        "${formatDate(event.startTime)} ${formatDate(event.endTime)}",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(16.dp))
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = event.description
                )
                HorizontalDivider(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Tapahtuma-ajat"
                )
                if (event.dates.isNotEmpty()) {
                    Column {
                        event.dates.forEach { date ->
                            Text(
                                text = "${formatDate(date.start)} ${formatTime(date.start)}",
                            )
                        }

                    }
                }
            }
        }
    }
}
