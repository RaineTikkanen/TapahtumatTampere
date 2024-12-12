package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.utils.findNextDates
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCarousel(navController: NavController, events: List<Event>) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState{events.count()},
        preferredItemWidth = 300.dp,
        itemSpacing = 8.dp,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i->
        val nextDates = findNextDates(events[i].dates)
        val event = events[i]
        val painter = rememberAsyncImagePainter(event.image)
        val imageSize = Modifier.size(width = 310.dp, height = 310.dp)
        val textColor =MaterialTheme.colorScheme.primaryContainer
        val colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.onPrimaryContainer
        )
        val brush = Brush.verticalGradient(colors = colors)

        Box(
            modifier = Modifier
                .maskClip(MaterialTheme.shapes.extraLarge)
                .clickable(onClick = { navController.navigate(route = Info(event.id)) })
        )
        {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageSize
                    .clip(RoundedCornerShape(12.dp))
            )

            Canvas(
                modifier = imageSize,
                onDraw = {
                    drawRect(brush)
                }
            )
            Box(
                modifier = imageSize
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        color = textColor,
                        text = event.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2
                    )
                    Row {
                        if (nextDates.isNotEmpty()) {
                            Text(
                                    color = textColor,
                                    modifier = Modifier.padding(end = 8.dp),
                                    text = formatDate(nextDates[0].start)+" ${formatTime(nextDates[0].start)} - ${formatTime(nextDates[0].end)}",
                                    style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }

    }
}