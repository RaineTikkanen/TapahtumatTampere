package com.example.tapahtumattampere.ui.screens.eventList


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.utils.formatAddress
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime
import com.example.tapahtumattampere.utils.parseDate

@Composable
fun EventListComponent(event: Event, navController: NavController) {
    val startTime = parseDate(event.start_time)
    val endTime = parseDate(event.end_time)
    val imageUrl = event.images[0].url
    val painter = rememberAsyncImagePainter(imageUrl)

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = { navController.navigate(route = Info(event.id)) })
    )
    {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 300.dp, height = 150.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (startTime.toLocalDate() == endTime.toLocalDate()) {
                Text(
                    text = "${formatDate(startTime)} ${formatTime(startTime)} - ${formatTime(endTime)}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            } else {
                Text(
                    text = "${formatDate(startTime)} - ${formatDate(endTime)}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
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