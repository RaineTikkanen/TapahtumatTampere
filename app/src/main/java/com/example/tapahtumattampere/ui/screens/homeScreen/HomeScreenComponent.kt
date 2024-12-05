package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.domain.model.Event


@Composable
fun HomeScreenComponent(event: Event, navController: NavController, time: Time) {
    //val nextDate = findNextDates(event.dates)
    val painter = rememberAsyncImagePainter(event.image)
    val colors = listOf(
       Color.Transparent,
       MaterialTheme.colorScheme.surfaceVariant
    )
    val brush = Brush.verticalGradient(colors=colors)

    Box(
        modifier = Modifier
            .clickable(onClick = { navController.navigate(route = Info(event.id)) })
    )
    {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 310.dp, height = 310.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column{
            Canvas(
                modifier = Modifier.size(width = 310.dp, height = 310.dp),
                onDraw = {
                    drawRect(brush)
                }
            )
        }
        Box(
            modifier = Modifier
                .size(width = 310.dp, height = 310.dp)
                .wrapContentHeight(Alignment.Bottom)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = event.name,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    maxLines = 2
                )
                /*
                if (time == Time.TIME&&nextDate.isNotEmpty()) {
                    Text(
                        text = formatTime(nextDate[0]),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)

                    )
                }
                else if (time == Time.DAY&&nextDate.isNotEmpty()) {
                    Text(
                        text = "${formatDay(nextDate[0])}  ${formatTime(nextDate[0])}",
                        fontSize = 18.sp,)
                }
                else if(time == Time.DATE&&nextDate.isNotEmpty()) {
                    Text(
                        text = "${formatDate(nextDate[0])}  ${formatTime(nextDate[0])}",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }*/
            }
        }
    }
}