package com.example.tapahtumattampere.ui.bottomNavBar


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tapahtumattampere.icons.ExploreIcon
import com.example.tapahtumattampere.icons.ExploreIconFilled
import com.example.tapahtumattampere.icons.HomeIcon
import com.example.tapahtumattampere.icons.HomeIconFilled


data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

@Composable
fun BottomNavigationBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val navItems = listOf(
        NavItem(
            title = "Home",
            selectedIcon = HomeIconFilled,
            unselectedIcon = HomeIcon,
            route = "home"
        ),
        NavItem(
            title = "Explore",
            selectedIcon = ExploreIconFilled,
            unselectedIcon = ExploreIcon,
            route = "explore"
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

        BottomAppBar{
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

