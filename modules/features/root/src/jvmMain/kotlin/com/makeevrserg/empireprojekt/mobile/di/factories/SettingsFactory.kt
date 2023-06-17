package com.makeevrserg.empireprojekt.mobile.di.factories

import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Factory
import com.russhwolf.settings.Settings
@Suppress("UnusedPrivateMember")
actual class SettingsFactory actual constructor(
    private val configuration: PlatformConfiguration
) : Factory<Settings> {
    override fun create(): Settings {
        TODO()
    }
}
