package com.example.tapahtumattampere.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MuseumIconFilled: ImageVector
    get() {
        if (_MuseumIconFilled != null) {
            return _MuseumIconFilled!!
        }
        _MuseumIconFilled = ImageVector.Builder(
            name = "MuseumIconFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(80f, 880f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-360f)
                lineTo(80f, 440f)
                verticalLineToRelative(-80f)
                lineToRelative(400f, -280f)
                lineToRelative(400f, 280f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(360f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                lineTo(80f, 880f)
                close()
                moveTo(320f, 720f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-160f)
                lineToRelative(80f, 120f)
                lineToRelative(80f, -120f)
                verticalLineToRelative(160f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-280f)
                horizontalLineToRelative(-80f)
                lineToRelative(-80f, 120f)
                lineToRelative(-80f, -120f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(280f)
                close()
            }
        }.build()

        return _MuseumIconFilled!!
    }

@Suppress("ObjectPropertyName")
private var _MuseumIconFilled: ImageVector? = null
