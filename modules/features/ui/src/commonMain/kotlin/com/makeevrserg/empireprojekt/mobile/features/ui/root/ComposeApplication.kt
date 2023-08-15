package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.LocalAppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.PreviewThemeSwitcher
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher

fun ThemeSwitcher.Theme.toComposeTheme() = when (this) {
    ThemeSwitcher.Theme.DARK -> AppTheme.DefaultDarkTheme
    ThemeSwitcher.Theme.LIGHT -> AppTheme.DefaultLightTheme
}

@Composable
fun ComposeApplication(themeSwitcher: ThemeSwitcher = PreviewThemeSwitcher(), content: @Composable () -> Unit) {
    val theme by themeSwitcher.theme.collectAsState()
    val appTheme = theme.toComposeTheme()
    TransparentBars(appTheme.isDark)

    Crossfade(
        targetState = appTheme,
    ) { appTheme ->
        CompositionLocalProvider(
            LocalAppTheme provides appTheme,
            content = {
                MaterialTheme(
                    colors = LocalAppTheme.current.materialColor,
                    typography = LocalAppTheme.current.typography,
                    shapes = LocalAppTheme.current.shapes,
                    content = content
                )
            }
        )
    }
}
