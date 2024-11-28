package com.example.tapahtumattampere.ui.screens.HomeScreen

import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.R
import com.example.tapahtumattampere.data.Event
import com.example.tapahtumattampere.utils.formatAddress
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime

@Composable
fun HomeScreenComponent(event: Event?, navController: NavController) {
    if (event != null) {
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
                .clickable(onClick = { navController.navigate(route = Info(event.id)) })
                .size(width = 300.dp, height = 350.dp)
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.images[0].url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.generic_event_andrew_knechel),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 400.dp, height = 200.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = event.name,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                if (event.start_time != null) {
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
}