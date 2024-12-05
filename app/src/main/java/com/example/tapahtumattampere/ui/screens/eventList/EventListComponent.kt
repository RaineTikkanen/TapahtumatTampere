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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime


@Composable
fun EventListComponent(
    event: Event,
    onEventClick: (String) -> Unit,
    ) {
    val imageUrl = event.image
    val painter = rememberAsyncImagePainter(imageUrl)

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = { onEventClick(event.id) })
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
            if (event.startTime.toLocalDate() == event.endTime.toLocalDate()) {
                Text(
                    text = "${formatDate(event.startTime)} ${formatTime(event.startTime)} - ${formatTime(event.endTime)}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            } else {
                Text(
                    text = "${formatDate(event.startTime)} - ${formatDate(event.endTime)}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}