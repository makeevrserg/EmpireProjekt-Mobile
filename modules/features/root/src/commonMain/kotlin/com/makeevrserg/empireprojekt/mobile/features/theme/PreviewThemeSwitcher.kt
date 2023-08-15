package com.makeevrserg.empireprojekt.mobile.features.theme

import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.klibs.mikro.core.util.next

class PreviewThemeSwitcher : ThemeSwitcher {
    override val theme: MutableStateFlow<ThemeSwitcher.Theme> =
        MutableStateFlow(ThemeSwitcher.Theme.LIGHT)

    override fun selectDarkTheme() {
        selectTheme(ThemeSwitcher.Theme.DARK)
    }

    override fun selectLightTheme() {
        selectTheme(ThemeSwitcher.Theme.LIGHT)
    }

    override fun selectTheme(theme: ThemeSwitcher.Theme) {
        this.theme.value = theme
    }

    override fun next() {
        theme.value.next(ThemeSwitcher.Theme.values()).run(::selectTheme)
    }
}
