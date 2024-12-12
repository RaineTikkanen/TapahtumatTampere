package com.example.tapahtumattampere.ui.screens.eventInfoScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.tapahtumattampere.R
import com.example.tapahtumattampere.ui.screens.BackGroundGradient
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime

@Composable
fun EventInfo(id: String, navController: NavController) {
    val context = LocalContext.current
    val lang: String = getString(context,R.string.lang)
    val viewModel = viewModel {
        EventInfoViewModel(id, lang)
    }
    val event = viewModel.event
    when (viewModel.state) {
        EventInfoViewModelState.Loading -> LoadingScreen()
        EventInfoViewModelState.Error -> ErrorScreen()
        EventInfoViewModelState.Success -> {
            BackGroundGradient()
            if (event != null) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "BackArrow"
                        )
                    }
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(16.dp)
                            .height(250.dp)
                            .clip(MaterialTheme.shapes.extraLarge)
                        ,
                        model = event.image,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    if (event.address != null) {
                        Column {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = MaterialTheme.shapes.extraLarge,
                                modifier = Modifier
                                    .padding(bottom = 16.dp, top = 16.dp)
                            ) {
                                Row(modifier = Modifier.padding(16.dp)) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Location",
                                        modifier = Modifier.align(CenterVertically)
                                    )
                                    Column(
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                    ) {
                                        Text(
                                            style = MaterialTheme.typography.titleMedium,
                                            text = event.address.name,
                                        )
                                        if (event.address.address != null || event.address.zipCode != null || event.address.city != null) {
                                            Text(
                                                style = MaterialTheme.typography.titleMedium,
                                                text = "${event.address.address ?: ""}, ${event.address.zipCode ?: ""} ${event.address.city ?: ""}",
                                            )
                                        }
                                    }
                                }
                            }
                            Box (
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .clickable { viewModel.openMaps(context) }
                            ){
                                Row {
                                    Text(
                                        text= stringResource(R.string.ShowOnMap),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Arrow"
                                    )
                                }
                            }
                        }
                    }
                    Card (
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .padding(bottom = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            text = event.description
                        )
                    }
                    Box (
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { viewModel.openUrl(context) }
                    ){
                        Row {
                            Text(
                                text= stringResource(R.string.OpenInBrowser),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Arrow"
                            )
                        }
                    }

                    Card (
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .padding(bottom = 16.dp, top = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.EventTimes)
                            )
                            if (event.dates.isNotEmpty()) {
                                Column {
                                    event.dates.forEach { date ->
                                        if (date.start.toLocalDate() == date.end.toLocalDate()) {
                                            Text(text = "${formatDate(date.start)} ${formatTime(date.start)} - ${formatTime(date.end)}")
                                        } else {
                                            Text(text = "${formatDate(date.start)} - ${formatDate(date.end)}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}