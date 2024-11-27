package com.example.tapahtumattampere.ui.bottomNavBar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tapahtumattampere.icons.BaselineMuseum
import com.example.tapahtumattampere.icons.SportTennis
import com.example.tapahtumattampere.icons.TheaterComedy


@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate("museum") }) {
                Icon(BaselineMuseum, contentDescription = "Museums")
            }
        }
    )
}