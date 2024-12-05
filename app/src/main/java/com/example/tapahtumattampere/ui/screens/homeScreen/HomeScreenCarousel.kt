package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tapahtumattampere.domain.model.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCarousel(navController: NavController, events: List<Event>, time: Time) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState{events.count()},
        preferredItemWidth = 300.dp,
        itemSpacing = 16.dp,
        modifier= Modifier
            .size(height = 280.dp, width = 400.dp),
        contentPadding = PaddingValues(16.dp)
    ) { i ->
        Box(modifier = Modifier
            .maskClip(MaterialTheme.shapes.extraLarge)) {
            HomeScreenComponent(events[i], navController, time)
        }
    }
}

@Composable
fun HomeScreenPager(navController: NavController, events: List<Event>) {}