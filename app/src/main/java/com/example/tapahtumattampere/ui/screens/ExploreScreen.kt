package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.tapahtumattampere.icons.MuseumIcon
import com.example.tapahtumattampere.icons.MuseumIconFilled
import com.example.tapahtumattampere.icons.TheaterIcon
import com.example.tapahtumattampere.icons.TheaterIconFilled
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.eventList.EventList
import com.example.tapahtumattampere.ui.screens.eventList.EventUiState
import com.example.tapahtumattampere.ui.screens.eventList.FILTER


@Composable
fun ExploreScreen(headerViewModel: HeaderViewModel, navController: NavController, eventUiState: EventUiState) {
    headerViewModel.updateHeaderText("Explore")

    EventList(navController, eventUiState)
}