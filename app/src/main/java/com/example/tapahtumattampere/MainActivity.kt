package com.example.tapahtumattampere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tapahtumattampere.ui.headerBar.HeaderBar
import com.example.tapahtumattampere.ui.theme.TapahtumatTampereTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tapahtumattampere.ui.bottomNavBar.BottomNavigationBar
import com.example.tapahtumattampere.ui.screens.EventInfo
import com.example.tapahtumattampere.ui.screens.EventList.EventList
import com.example.tapahtumattampere.ui.screens.EventList.EventViewModel
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.HomeScreen
import com.example.tapahtumattampere.ui.screens.MuseumScreen
import com.example.tapahtumattampere.ui.screens.SportScreen
import com.example.tapahtumattampere.ui.screens.TheatreScreen
import kotlinx.serialization.Serializable


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
    val headerViewModel: HeaderViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    when (navBackStackEntry?.destination?.route) {
        "homeScreen" -> bottomBarState.value = true
        "museumScreen" -> bottomBarState.value = true
        "sportScreen" -> bottomBarState.value = true
        "theatreScreen" -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }
    TapahtumatTampereTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            topBar = { HeaderBar(scrollBehavior, headerViewModel) },
            bottomBar = {BottomNavigationBar(navController, bottomBarState)},
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        )
        { innerPadding ->
            Column(modifier=Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable("homeScreen") { HomeScreen(headerViewModel, navController, eventViewModel)}
                    composable("museumScreen") { MuseumScreen(headerViewModel, navController, eventViewModel)}
                    composable("sportScreen") { SportScreen(headerViewModel, navController, eventViewModel)}
                    composable("theatreScreen") { TheatreScreen(headerViewModel, navController, eventViewModel)}
                    composable<Info>{backStackEntry->
                        val info:Info=backStackEntry.toRoute()
                        EventInfo(headerViewModel, info.id, eventViewModel.eventUiState)
                    }
                }
            }
        }
    }
}

@Serializable
data class Info(val id: String)