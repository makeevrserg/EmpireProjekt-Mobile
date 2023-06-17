package com.makeevrserg.empireprojekt.mobile.features.root.di.factories

import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Factory
import com.russhwolf.settings.Settings

expect class SettingsFactory(
    configuration: PlatformConfiguration
) : Factory<Settings>
