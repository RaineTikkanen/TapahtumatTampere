package com.example.tapahtumattampere.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BaselineMuseum: ImageVector
    get() {
        if (_BaselineMuseum != null) {
            return _BaselineMuseum!!
        }
        _BaselineMuseum = ImageVector.Builder(
            name = "BaselineMuseum",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(22f, 11f)
                verticalLineTo(9f)
                lineTo(12f, 2f)
                lineTo(2f, 9f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(9f)
                horizontalLineTo(2f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(20f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-9f)
                horizontalLineTo(22f)
                close()
                moveTo(16f, 18f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-4f)
                lineToRelative(-2f, 3f)
                lineToRelative(-2f, -3f)
                verticalLineToRelative(4f)
                horizontalLineTo(8f)
                verticalLineToRelative(-7f)
                horizontalLineToRelative(2f)
                lineToRelative(2f, 3f)
                lineToRelative(2f, -3f)
                horizontalLineToRelative(2f)
                verticalLineTo(18f)
                close()
            }
        }.build()

        return _BaselineMuseum!!
    }

@Suppress("ObjectPropertyName")
private var _BaselineMuseum: ImageVector? = null
