package com.example.tapahtumattampere.ui.screens.exploreScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.tapahtumattampere.Info
import com.example.tapahtumattampere.R
import com.example.tapahtumattampere.ui.screens.BackGroundGradient
import com.example.tapahtumattampere.ui.screens.ErrorScreen
import com.example.tapahtumattampere.ui.screens.LoadingScreen
import com.example.tapahtumattampere.utils.formatDate
import com.example.tapahtumattampere.utils.formatTime


@Composable
fun ExploreScreen(navController: NavController) {
    val context = LocalContext.current
    val lang: String = getString(context, R.string.lang)
    val viewModel = viewModel {
        ExploreScreenViewModel(lang)
    }

    val searchText by viewModel.searchText.collectAsState()
    val searchBarVisible by viewModel.searchBarVisible.collectAsState()
    val events by viewModel.events.collectAsState()

    when (viewModel.state) {
        ExploreScreenViewModelState.Loading -> LoadingScreen()
        ExploreScreenViewModelState.Error -> ErrorScreen()
        ExploreScreenViewModelState.Success -> {
            BackGroundGradient()
            Column {
                Box {
                    HeaderText(searchBarVisible, searchText)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.End)
                            .height(60.dp)
                    ) {
                        Box(modifier=Modifier.weight(7f)) {
                            SearchBar(
                                searchText = searchText,
                                searchBarVisible = searchBarVisible,
                                onSearchTextChange = { viewModel.onSearchTextChange(it) },
                                onSearch = { viewModel.toggleSearchBarVisibility() }
                            )
                        }
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.toggleSearchBarVisibility() }
                        ) {
                            Icon(
                                imageVector = if (searchBarVisible) Icons.Default.Close else Icons.Default.Search,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = stringResource(R.string.search)
                            )
                        }
                    }
                }
                if (events.isNotEmpty()) {
                    Column{
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalItemSpacing = 5.dp,
                        ) {
                            items(events.size, key = { events[it].id }) {
                                val event = events[it]
                                val imageUrl = event.image
                                val painter = rememberAsyncImagePainter(imageUrl)

                                ElevatedCard(
                                    shape = MaterialTheme.shapes.medium,
                                    colors = androidx.compose.material3.CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                        .clickable(onClick = { navController.navigate(Info(event.id)) })
                                )
                                {
                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(width = 300.dp, height = 150.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                    )
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = event.name,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )
                                        if (event.startTime.toLocalDate() == event.endTime.toLocalDate()) {
                                            Text(
                                                text = "${formatDate(event.startTime)} ${
                                                    formatTime(
                                                        event.startTime
                                                    )
                                                } - ${formatTime(event.endTime)}",
                                                fontSize = 12.sp,
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            )
                                        } else {
                                            Text(
                                                text = "${formatDate(event.startTime)} - ${
                                                    formatDate(
                                                        event.endTime
                                                    )
                                                }",
                                                fontSize = 12.sp,
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = stringResource(R.string.no_events_found),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit, searchBarVisible: Boolean, onSearch: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    AnimatedVisibility(
        visible = searchBarVisible,
        enter = expandHorizontally() + fadeIn(),
        exit = shrinkHorizontally() + fadeOut()
    ) {

        TextField(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor =  Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                cursorColor = MaterialTheme.colorScheme.primary,

            ),
            shape = MaterialTheme.shapes.extraLarge,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch =  {focusManager.clearFocus()}),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            value = searchText,
            onValueChange = onSearchTextChange,
            placeholder = { Text(stringResource(R.string.search)) },
        )
    }
}

@Composable
fun HeaderText(searchBarVisible: Boolean, searchText: String) {
    AnimatedVisibility(
        visible = !searchBarVisible,
        enter = expandHorizontally() + fadeIn(),
        exit = shrinkHorizontally() + fadeOut()
    ) {
        Text(
            maxLines = 1,
            text = stringResource(R.string.explore),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}