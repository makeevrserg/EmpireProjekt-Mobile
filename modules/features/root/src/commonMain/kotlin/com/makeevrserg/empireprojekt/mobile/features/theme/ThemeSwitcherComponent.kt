package com.makeevrserg.empireprojekt.mobile.features.theme

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.kstorage.StateFlowMutableStorageValue
import ru.astrainteractive.klibs.mikro.core.util.next

class ThemeSwitcherComponent(private val settings: Settings) : ThemeSwitcher {
    private val key = "THEME"
    private val default = ThemeSwitcher.Theme.DARK
    private val themeFlowStorageValue = StateFlowMutableStorageValue(
        default = default,
        loadSettingsValue = {
            val ordinal = settings.getInt(key, ThemeSwitcher.Theme.LIGHT.ordinal)
            ThemeSwitcher.Theme.values().getOrNull(ordinal) ?: default
        },
        saveSettingsValue = {
            settings.putInt(key, it.ordinal)
        }
    )
    override val theme: StateFlow<ThemeSwitcher.Theme> = themeFlowStorageValue.stateFlow

    override fun selectDarkTheme() {
        themeFlowStorageValue.save(ThemeSwitcher.Theme.DARK)
    }

    override fun selectLightTheme() {
        themeFlowStorageValue.save(ThemeSwitcher.Theme.LIGHT)
    }

    override fun selectTheme(theme: ThemeSwitcher.Theme) {
        themeFlowStorageValue.save(theme)
    }

    override fun next() {
        selectTheme(theme.value.next(ThemeSwitcher.Theme.values()))
    }

    init {
        themeFlowStorageValue.load()
    }
}
