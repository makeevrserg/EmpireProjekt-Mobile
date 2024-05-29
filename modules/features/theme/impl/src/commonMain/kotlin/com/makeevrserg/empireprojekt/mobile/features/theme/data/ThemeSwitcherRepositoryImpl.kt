package com.makeevrserg.empireprojekt.mobile.features.theme.data

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.StateFlowMutableStorageValue
import ru.astrainteractive.klibs.kstorage.api.value.ValueFactory

internal class ThemeSwitcherRepositoryImpl(
    private val settings: Settings
) : ThemeSwitcherRepository {

    private val key = "THEME"

    private val themeValueFactory = ValueFactory { Theme.DARK }

    override val themeFlowStorageValue = StateFlowMutableStorageValue(
        factory = themeValueFactory,
        loader = {
            val ordinal = settings.getInt(key, Theme.LIGHT.ordinal)
            Theme.entries.getOrNull(ordinal) ?: themeValueFactory.create()
        },
        saver = {
            settings.putInt(key, it.ordinal)
        }
    )
}
