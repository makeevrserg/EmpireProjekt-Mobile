package com.makeevrserg.empireprojekt.mobile.features.theme

import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.klibs.mikro.core.util.next

class PreviewThemeSwitcherComponent : ThemeSwitcherComponent {
    override val theme: MutableStateFlow<ThemeSwitcherComponent.Theme> =
        MutableStateFlow(ThemeSwitcherComponent.Theme.LIGHT)

    override fun selectDarkTheme() {
        selectTheme(ThemeSwitcherComponent.Theme.DARK)
    }

    override fun selectLightTheme() {
        selectTheme(ThemeSwitcherComponent.Theme.LIGHT)
    }

    override fun selectTheme(theme: ThemeSwitcherComponent.Theme) {
        this.theme.value = theme
    }

    override fun next() {
        theme.value.next(ThemeSwitcherComponent.Theme.values()).run(::selectTheme)
    }
}
