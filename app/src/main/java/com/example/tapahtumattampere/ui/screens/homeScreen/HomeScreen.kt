package com.example.tapahtumattampere.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tapahtumattampere.R
import com.example.tapahtumattampere.ui.screens.BackGroundGradient
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen


@Composable
fun HomeScreen( navController: NavController) {
    val context = LocalContext.current
    val lang: String = getString(context, R.string.lang)
    val viewModel = viewModel {
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
                Text(
                    text = stringResource(R.string.events),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = stringResource(R.string.today),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsToday)

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(R.string.this_week),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsThisWeek)
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(R.string.this_month),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
                HomeScreenCarousel(navController, viewModel.eventsThisMonth)
            }
        }
    }
}