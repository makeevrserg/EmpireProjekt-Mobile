package com.makeevrserg.empireprojekt.mobile.features.theme.data

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.StateFlowMutableStorageValue

class ThemeSwitcherRepositoryImpl(
    private val settings: Settings
) : ThemeSwitcherRepository {

    private val key = "THEME"

    private val default = Theme.DARK

    override val themeFlowStorageValue = StateFlowMutableStorageValue(
        default = default,
        loadSettingsValue = {
            val ordinal = settings.getInt(key, Theme.LIGHT.ordinal)
            Theme.entries.getOrNull(ordinal) ?: default
        },
        saveSettingsValue = {
            settings.putInt(key, it.ordinal)
        }
    )
}
