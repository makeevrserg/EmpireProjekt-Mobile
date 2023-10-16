package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun GradientProgressIndicator(
    brush: Brush,
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.materialColor.primaryVariant
) {
    Box(modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        ) {
            drawRect(
                color = backgroundColor,
                topLeft = Offset(x = 0f, y = 0f),
                size = Size(size.width, size.height)
            )
            drawRect(
                brush = brush,
                topLeft = Offset(x = 0f, y = 0f),
                size = Size(progress * size.width, size.height)
            )
        }
    }
}
