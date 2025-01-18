package com.example.tapahtumattampere.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

@Composable
fun BackGroundGradient() {
    val colors = listOf(
        MaterialTheme.colorScheme.surface,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
    )
    val brush = Brush.verticalGradient(colors = colors)
    Canvas(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        onDraw = {
            drawRect(brush)
        }
    )
}