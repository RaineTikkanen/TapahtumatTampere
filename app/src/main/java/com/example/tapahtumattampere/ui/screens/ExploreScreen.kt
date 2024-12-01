package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.tapahtumattampere.icons.MuseumIcon
import com.example.tapahtumattampere.icons.MuseumIconFilled
import com.example.tapahtumattampere.icons.TheaterIcon
import com.example.tapahtumattampere.icons.TheaterIconFilled
import com.example.tapahtumattampere.ui.headerBar.HeaderViewModel
import com.example.tapahtumattampere.ui.screens.eventList.EventList
import com.example.tapahtumattampere.ui.screens.eventList.EventUiState
import com.example.tapahtumattampere.ui.screens.eventList.FILTER


class FilterChipItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val filterContent: FILTER,
)

@Composable
fun FilterChipExample(filterChipItem: FilterChipItem, filter: MutableState<FILTER>) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = {
            selected = !selected
            if (selected) {
                filter.value = filterChipItem.filterContent
            }
                  },
        label = {
            Text(filterChipItem.title)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = filterChipItem.selectedIcon,
                    contentDescription = filterChipItem.title,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Composable
fun ExploreScreen(headerViewModel: HeaderViewModel, navController: NavController, eventUiState: EventUiState){
    headerViewModel.updateHeaderText("Explore")
    val filter by remember { mutableStateOf(FILTER.ALL) }
    val filterChips = listOf(
       FilterChipItem(
            title = "Museot",
            selectedIcon = MuseumIconFilled,
            unselectedIcon = MuseumIcon,
            filterContent = FILTER.MUSEUM
        ),
        FilterChipItem(
            title = "Teatterit",
            selectedIcon = TheaterIconFilled,
            unselectedIcon = TheaterIcon,
            filterContent = FILTER.THEATRE
        ),
    )

    Column {
        Row{
            filterChips.forEach { filterChip ->
                FilterChipExample(filterChipItem = filterChip, filter = mutableStateOf(filter))
            }
        }
        EventList(navController, eventUiState, filter)
    }
}