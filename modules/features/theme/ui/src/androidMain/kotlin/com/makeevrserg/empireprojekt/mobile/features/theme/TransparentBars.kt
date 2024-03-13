package com.makeevrserg.empireprojekt.mobile.features.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal actual fun TransparentBars(isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, isDarkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
        onDispose {
        }
    }
}
