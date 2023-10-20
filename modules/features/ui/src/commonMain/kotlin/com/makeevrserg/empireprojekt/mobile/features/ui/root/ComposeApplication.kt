package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.Material2Theme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.Material3Theme
import com.makeevrserg.empireprojekt.mobile.features.theme.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme

fun Theme.wrap() = when (this) {
    Theme.DARK -> AppTheme(
        material2Theme = Material2Theme.DefaultDark,
        material3Theme = Material3Theme.DefaultDark,
        isDark = true
    )

    Theme.LIGHT -> AppTheme(
        material2Theme = Material2Theme.DefaultLight,
        material3Theme = Material3Theme.DefaultLight,
        isDark = false
    )
}

@Composable
fun ComposeApplication(
    themeSwitcherComponent: ThemeSwitcherComponent = PreviewThemeSwitcherComponent(),
    content: @Composable () -> Unit
) {
    val theme by themeSwitcherComponent.theme.collectAsState()
    val themeWrapper = theme.wrap()
    TransparentBars(themeWrapper.isDark)

    AdaptThemeFade(themeWrapper) {
        content.invoke()
    }
}
