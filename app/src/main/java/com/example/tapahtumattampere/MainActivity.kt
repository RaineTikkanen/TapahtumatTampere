package com.example.tapahtumattampere

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tapahtumattampere.data.MainViewModel
import com.example.tapahtumattampere.ui.composables.HeaderBar
import com.example.tapahtumattampere.ui.theme.TapahtumatTampereTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tapahtumattampere.ui.composables.EventInfo
import com.example.tapahtumattampere.ui.composables.EventList


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
    val navController = rememberNavController()
    TapahtumatTampereTheme {
        Scaffold(
            topBar = { HeaderBar("Tapahtumat") },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier=Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = "eventList") {
                    composable("eventList") {EventList(navController)}
                    composable("info"){EventInfo()}
                }
            }
        }
    }
}