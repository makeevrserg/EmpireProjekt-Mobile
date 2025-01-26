package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

class AppTheme(
    val material2Theme: Material2Theme = Material2Theme.DefaultDark,
    val material3Theme: Material3Theme = Material3Theme.DefaultDark,
    val isDark: Boolean = true,
    val dimens: Dimens = Dimens(),
    val customColors: CustomColors = CustomColors()
) {
    companion object {
        val customColors: CustomColors
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.customColors
        val dimens: Dimens
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.dimens
    }
}
