package com.makeevrserg.empireprojekt.mobile.features.theme.data

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultStateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.provider.DefaultValueFactory

internal class ThemeSwitcherRepositoryImpl(
    private val settings: Settings
) : ThemeSwitcherRepository {

    private val key = "THEME"

    private val themeValueFactory = DefaultValueFactory { Theme.DARK }

    override val themeFlowStorageValue = DefaultStateFlowMutableKrate(
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
