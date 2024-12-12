package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tapahtumattampere.R
import com.example.tapahtumattampere.icons.DarkModeIcon
import com.example.tapahtumattampere.icons.LightModeIcon
import com.example.tapahtumattampere.ui.screens.BackGroundGradient
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen


@Composable
fun HomeScreen( navController: NavController, darkTheme: MutableState<Boolean>) {
    val context = LocalContext.current
    val lang: String = getString(context,R.string.lang)
    println(lang)
    val viewModel=viewModel{
        HomeScreenViewModel(lang)
    }

    when (viewModel.state) {
        HomeScreenViewModelState.Loading -> LoadingScreen()
        HomeScreenViewModelState.Error -> ErrorScreen()
        HomeScreenViewModelState.Success -> {
            BackGroundGradient()
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.events),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterVertically)
                            .wrapContentWidth(Alignment.End)
                    ) {
                        ThemeSwitcher(darkTheme = darkTheme.value) {
                            darkTheme.value = !darkTheme.value
                        }
                    }
                }
                Text(
                    text = stringResource(R.string.today),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start=16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsToday)

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(R.string.this_week),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start=16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsThisWeek)
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(R.string.this_month),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start=16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsThisMonth)
            }
        }
    }
}

@Composable
fun ThemeSwitcher(darkTheme: Boolean, onThemeChange: () -> Unit) {
    Switch(
        checked = darkTheme,
        onCheckedChange = { onThemeChange() },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        thumbContent = {
            if(darkTheme){
                Icon(
                    imageVector = DarkModeIcon,
                    contentDescription = "Light Mode"
                )
            }else{
                Icon(
                    imageVector = LightModeIcon,
                    contentDescription = "Dark Mode"
                )
            }
        }
    )
}