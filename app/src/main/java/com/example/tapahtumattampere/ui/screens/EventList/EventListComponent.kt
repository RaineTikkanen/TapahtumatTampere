package com.example.tapahtumattampere.ui.screens.EventList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.utils.formatAddress
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime

@Composable
fun EventListComponent(event: Event, navController: NavController) {
    val startDate = formatDate(event.start_time)
    val endDate = formatDate(event.end_time)
    val startTime = formatTime(event.start_time)
    val endTime = formatTime(event.end_time)

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = {navController.navigate("info")})
    )
    {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = event.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (event.start_time != null) {
                if (startDate == endDate) {
                    Text(
                        text = "$startDate $startTime - $endTime",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                } else {
                    Text(
                        text = "$startDate - $endDate",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            if (event.locations.isNotEmpty()) {
                val address = formatAddress(event.locations[0].address)

                Text(
                    text = address.name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }

}