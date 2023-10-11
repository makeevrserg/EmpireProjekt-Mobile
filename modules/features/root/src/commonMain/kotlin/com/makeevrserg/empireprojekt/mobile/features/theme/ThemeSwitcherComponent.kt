package com.makeevrserg.empireprojekt.mobile.features.theme

import kotlinx.coroutines.flow.StateFlow

interface ThemeSwitcherComponent {
    val theme: StateFlow<Theme>

    enum class Theme {
        DARK, LIGHT
    }

    fun selectDarkTheme()
    fun selectLightTheme()
    fun selectTheme(theme: Theme)
    fun next()
}
