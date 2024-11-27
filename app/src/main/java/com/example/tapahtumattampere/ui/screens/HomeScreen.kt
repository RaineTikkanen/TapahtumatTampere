package com.example.tapahtumattampere.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.EventList.EventList
import com.example.tapahtumattampere.ui.screens.EventList.EventViewModel

@Composable
fun HomeScreen(headerViewModel: HeaderViewModel, navController: NavController, eventViewModel: EventViewModel){
    headerViewModel.updateHeaderText("Home")
    EventList(navController, eventViewModel.eventUiState, headerViewModel)
}