package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.LocalAppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher

private fun ThemeSwitcher.Theme.toComposeTheme() = when (this) {
    ThemeSwitcher.Theme.DARK -> AppTheme.DefaultDarkTheme
    ThemeSwitcher.Theme.LIGHT -> AppTheme.DefaultLightTheme
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ComposeApplication(themeSwitcher: ThemeSwitcher, content: @Composable () -> Unit) {
    val theme by themeSwitcher.theme.collectAsState()
    val appTheme = theme.toComposeTheme()
    TransparentBars(appTheme.isDark)

    AnimatedContent(
        targetState = appTheme,
        transitionSpec = { fadeIn() with fadeOut() }
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
