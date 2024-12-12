package com.example.tapahtumattampere

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tapahtumattampere.ui.theme.TapahtumatTampereTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tapahtumattampere.ui.bottomNavBar.BottomNavigationBar
import com.example.tapahtumattampere.ui.screens.eventInfoScreen.EventInfo
import com.example.tapahtumattampere.ui.screens.exploreScreen.ExploreScreen
import com.example.tapahtumattampere.ui.screens.homeScreen.HomeScreen
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

@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val darkTheme= rememberSaveable { (mutableStateOf(true)) }
    when (currentDestination?.route) {
        "home" -> bottomBarState.value = true
        "explore" -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }

    TapahtumatTampereTheme(darkTheme=darkTheme.value) {
        Scaffold(
            bottomBar = {BottomNavigationBar(navController, bottomBarState)},
            modifier = Modifier
                .fillMaxSize()
        )
        { innerPadding ->
            val bottomBarPadding= animateDpAsState(targetValue = if (bottomBarState.value) innerPadding.calculateBottomPadding() else 24.dp)
            Column(modifier=Modifier
                .padding(top=innerPadding.calculateTopPadding(), bottom=bottomBarPadding.value)
            )
            {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController, darkTheme) }
                    composable("explore") { ExploreScreen(navController) }
                    composable<Info>{backStackEntry->
                        val info:Info=backStackEntry.toRoute()
                        EventInfo(info.id, navController)
                    }
                }
            }
        }
    }
}



@Serializable
data class Info(val id: String)