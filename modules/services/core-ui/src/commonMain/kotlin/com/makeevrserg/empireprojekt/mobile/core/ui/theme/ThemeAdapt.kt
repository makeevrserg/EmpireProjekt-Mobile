package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

@Composable
private fun Material3ThemeAdapt(theme: Material3Theme, content: @Composable () -> Unit) {
    androidx.compose.material3.MaterialTheme(
        colorScheme = theme.colorScheme,
        shapes = theme.shapes,
        typography = theme.typography,
        content = content
    )
}

@Composable
private fun Material2ThemeAdapt(theme: Material2Theme, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = theme.colors,
        typography = theme.typography,
        shapes = theme.shapes,
        content = content
    )
}

@Composable
fun AdaptThemeFade(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    Crossfade(targetState = appTheme) { appTheme ->
        CompositionLocalProvider(
            LocalAppTheme provides appTheme,
            content = {
                Material2ThemeAdapt(theme = LocalAppTheme.current.material2Theme) {
                    Material3ThemeAdapt(theme = LocalAppTheme.current.material3Theme) {
                        content.invoke()
                    }
                }
            }
        )
    }
}

@Suppress("ComposableNaming", "CompositionLocalAllowlist")
internal val LocalAppTheme: ProvidableCompositionLocal<AppTheme> = compositionLocalOf {
    AppTheme()
}
