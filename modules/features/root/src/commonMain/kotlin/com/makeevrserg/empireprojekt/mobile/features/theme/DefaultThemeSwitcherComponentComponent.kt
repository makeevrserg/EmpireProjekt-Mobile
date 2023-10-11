package com.makeevrserg.empireprojekt.mobile.features.theme

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.util.next

class DefaultThemeSwitcherComponentComponent(
    themeSwitcherModule: ThemeSwitcherModule
) : ThemeSwitcherComponent, ThemeSwitcherModule by themeSwitcherModule {
    private val themeFlowStorageValue by Provider {
        themeSwitcherRepository.themeFlowStorageValue
    }

    override val theme: StateFlow<Theme> = themeFlowStorageValue.stateFlow

    override fun selectDarkTheme() {
        themeFlowStorageValue.save(Theme.DARK)
    }

    override fun selectLightTheme() {
        themeFlowStorageValue.save(Theme.LIGHT)
    }

    override fun selectTheme(theme: Theme) {
        themeFlowStorageValue.save(theme)
    }

    override fun next() {
        val entries = Theme.entries.toTypedArray()
        val nextTheme = theme.value.next(entries)
        selectTheme(nextTheme)
    }

    init {
        themeFlowStorageValue.load()
    }
}
