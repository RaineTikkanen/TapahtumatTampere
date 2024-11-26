package com.example.tapahtumattampere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tapahtumattampere.ui.screens.HeaderBar
import com.example.tapahtumattampere.ui.theme.TapahtumatTampereTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tapahtumattampere.ui.screens.EventInfo
import com.example.tapahtumattampere.ui.screens.EventList
import com.example.tapahtumattampere.ui.screens.EventViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val eventViewModel: EventViewModel = viewModel(factory = EventViewModel.Factory)
    val navController = rememberNavController()
    TapahtumatTampereTheme {
        Scaffold(
            topBar = { HeaderBar("Tapahtumat") },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier=Modifier.padding(innerPadding)) {
                Spacer(modifier = Modifier.padding(5.dp))
                NavHost(navController = navController, startDestination = "eventList") {
                    composable("eventList") {EventList(navController, eventViewModel)}
                    composable("info"){EventInfo()}
                }
            }
        }
    }
}