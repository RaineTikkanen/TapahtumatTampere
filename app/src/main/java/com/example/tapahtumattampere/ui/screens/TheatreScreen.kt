package com.example.tapahtumattampere.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.EventList.EventList
import com.example.tapahtumattampere.ui.screens.EventList.EventViewModel
import com.example.tapahtumattampere.ui.screens.EventList.ViewOptions

@Composable
fun TheatreScreen(headerViewModel: HeaderViewModel, navController: NavController, eventViewModel: EventViewModel){
    headerViewModel.updateHeaderText("Teatterit")
    EventList(navController, eventViewModel.eventUiState, viewOption = ViewOptions.THEATRE)

}