package com.example.tapahtumattampere.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MusicIcon: ImageVector
    get() {
        if (_MusicIcon != null) {
            return _MusicIcon!!
        }
        _MusicIcon = ImageVector.Builder(
            name = "MusicIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(400f, 840f)
                quadToRelative(-66f, 0f, -113f, -47f)
                reflectiveQuadToRelative(-47f, -113f)
                quadToRelative(0f, -66f, 47f, -113f)
                reflectiveQuadToRelative(113f, -47f)
                quadToRelative(23f, 0f, 42.5f, 5.5f)
                reflectiveQuadTo(480f, 542f)
                verticalLineToRelative(-422f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(160f)
                lineTo(560f, 280f)
                verticalLineToRelative(400f)
                quadToRelative(0f, 66f, -47f, 113f)
                reflectiveQuadToRelative(-113f, 47f)
                close()
            }
        }.build()

        return _MusicIcon!!
    }

@Suppress("ObjectPropertyName")
private var _MusicIcon: ImageVector? = null
