package com.example.tapahtumattampere.ui.bottomNavBar


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tapahtumattampere.icons.ExploreIcon
import com.example.tapahtumattampere.icons.ExploreIconFilled
import com.example.tapahtumattampere.icons.HomeIcon
import com.example.tapahtumattampere.icons.HomeIconFilled
import com.example.tapahtumattampere.icons.MuseumIcon
import com.example.tapahtumattampere.icons.MuseumIconFilled
import com.example.tapahtumattampere.icons.TennisIcon
import com.example.tapahtumattampere.icons.TennisIconFilled
import com.example.tapahtumattampere.icons.TheaterIcon
import com.example.tapahtumattampere.icons.TheaterIconFilled


data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route:String,
)

@Composable
fun BottomNavigationBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val navItems = listOf(
        NavItem(
            title = "Home",
            selectedIcon = HomeIconFilled,
            unselectedIcon = HomeIcon,
            route = "homeScreen"
        ),
        NavItem(
            title = "Explore",
            selectedIcon = ExploreIconFilled,
            unselectedIcon = ExploreIcon,
            route = "exploreScreen"
    )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    )
    {
        NavigationBar {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = if (currentRoute == item.route) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}
