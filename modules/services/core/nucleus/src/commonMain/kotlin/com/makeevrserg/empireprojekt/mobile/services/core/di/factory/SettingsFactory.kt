package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal expect class SettingsFactory(
    configuration: PlatformConfiguration
) : Factory<Settings>
