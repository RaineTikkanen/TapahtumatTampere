package com.example.tapahtumattampere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val eventViewModel: EventViewModel = viewModel(factory = EventViewModel.Factory)
    val navController = rememberNavController()
    TapahtumatTampereTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            topBar = { HeaderBar("Tapahtumat", scrollBehavior) },
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        )
        { innerPadding ->
            Column(modifier=Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = "eventList") {
                    composable("eventList") {EventList(navController, eventViewModel)}
                    composable("info"){EventInfo()}
                }
            }
        }
    }
}