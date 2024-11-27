package com.example.tapahtumattampere.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel

@Composable
fun TheatreScreen(headerViewModel: HeaderViewModel){
    headerViewModel.updateHeaderText("Theatres")
    Text("Theatres")
}