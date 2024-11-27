package com.example.tapahtumattampere.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HomeIconFilled: ImageVector
    get() {
        if (_HomeIconFilled != null) {
            return _HomeIconFilled!!
        }
        _HomeIconFilled = ImageVector.Builder(
            name = "HomeIconFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(160f, 840f)
                verticalLineToRelative(-480f)
                lineToRelative(320f, -240f)
                lineToRelative(320f, 240f)
                verticalLineToRelative(480f)
                lineTo(560f, 840f)
                verticalLineToRelative(-280f)
                lineTo(400f, 560f)
                verticalLineToRelative(280f)
                lineTo(160f, 840f)
                close()
            }
        }.build()

        return _HomeIconFilled!!
    }

@Suppress("ObjectPropertyName")
private var _HomeIconFilled: ImageVector? = null
