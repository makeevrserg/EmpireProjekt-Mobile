package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.LocalAppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme

fun Theme.toComposeTheme() = when (this) {
    Theme.DARK -> AppTheme.DefaultDarkTheme
    Theme.LIGHT -> AppTheme.DefaultLightTheme
}

@Composable
fun ComposeApplication(
    themeSwitcherComponent: ThemeSwitcherComponent = PreviewThemeSwitcherComponent(),
    content: @Composable () -> Unit
) {
    val theme by themeSwitcherComponent.theme.collectAsState()
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
