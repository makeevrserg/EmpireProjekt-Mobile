package com.makeevrserg.empireprojekt.mobile.features.theme

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.klibs.mikro.core.util.next

class PreviewThemeSwitcherComponent : ThemeSwitcherComponent {
    override val theme: MutableStateFlow<Theme> =
        MutableStateFlow(Theme.LIGHT)

    override fun selectDarkTheme() {
        selectTheme(Theme.DARK)
    }

    override fun selectLightTheme() {
        selectTheme(Theme.LIGHT)
    }

    override fun selectTheme(theme: Theme) {
        this.theme.value = theme
    }

    override fun next() {
        theme.value.next(Theme.values()).run(::selectTheme)
    }
}
