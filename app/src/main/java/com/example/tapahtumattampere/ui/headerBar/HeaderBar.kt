package com.example.tapahtumattampere.ui.headerBar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(scrollBehavior: TopAppBarScrollBehavior, headerViewModel: HeaderViewModel){
    val headerText = headerViewModel.headerText.observeAsState().value
    CenterAlignedTopAppBar(

        title = {
            Text(
                style = MaterialTheme.typography.titleLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                text = headerText?: "Tapahtumat Tampere"
            )
        },
        scrollBehavior = scrollBehavior
    )
}