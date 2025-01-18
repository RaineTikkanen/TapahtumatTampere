package com.example.tapahtumattampere.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MuseumIcon: ImageVector
    get() {
        if (_MuseumIcon != null) {
            return _MuseumIcon!!
        }
        _MuseumIcon = ImageVector.Builder(
            name = "MuseumIcon",
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
                moveTo(240f, 800f)
                horizontalLineToRelative(480f)
                horizontalLineToRelative(-480f)
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
                moveTo(720f, 800f)
                verticalLineToRelative(-454f)
                lineTo(480f, 178f)
                lineTo(240f, 346f)
                verticalLineToRelative(454f)
                horizontalLineToRelative(480f)
                close()
            }
        }.build()

        return _MuseumIcon!!
    }

@Suppress("ObjectPropertyName")
private var _MuseumIcon: ImageVector? = null
