package com.makeevrserg.empireprojekt.mobile.features.root.di.factories

import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

expect class SettingsFactory(
    configuration: PlatformConfiguration
) : Factory<Settings>
