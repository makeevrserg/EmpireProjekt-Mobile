package com.makeevrserg.empireprojekt.mobile.features.theme

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.kstorage.StateFlowMutableStorageValue
import ru.astrainteractive.klibs.mikro.core.util.next

class DefaultThemeSwitcherComponentComponent(
    private val settings: Settings
) : ThemeSwitcherComponent {

    private val key = "THEME"

    private val default = ThemeSwitcherComponent.Theme.DARK

    private val themeFlowStorageValue = StateFlowMutableStorageValue(
        default = default,
        loadSettingsValue = {
            val ordinal = settings.getInt(key, ThemeSwitcherComponent.Theme.LIGHT.ordinal)
            ThemeSwitcherComponent.Theme.entries.getOrNull(ordinal) ?: default
        },
        saveSettingsValue = {
            settings.putInt(key, it.ordinal)
        }
    )

    override val theme: StateFlow<ThemeSwitcherComponent.Theme> = themeFlowStorageValue.stateFlow

    override fun selectDarkTheme() {
        themeFlowStorageValue.save(ThemeSwitcherComponent.Theme.DARK)
    }

    override fun selectLightTheme() {
        themeFlowStorageValue.save(ThemeSwitcherComponent.Theme.LIGHT)
    }

    override fun selectTheme(theme: ThemeSwitcherComponent.Theme) {
        themeFlowStorageValue.save(theme)
    }

    override fun next() {
        val entries = ThemeSwitcherComponent.Theme.entries.toTypedArray()
        val nextTheme = theme.value.next(entries)
        selectTheme(nextTheme)
    }

    init {
        themeFlowStorageValue.load()
    }
}
