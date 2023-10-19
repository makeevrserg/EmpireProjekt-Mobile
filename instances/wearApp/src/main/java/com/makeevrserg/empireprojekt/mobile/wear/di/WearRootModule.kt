package com.makeevrserg.empireprojekt.mobile.wear.di

import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface WearRootModule : Module {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val settings: Single<Settings>
    val jsonConfiguration: Single<Json>
    val themeSwitcherComponent: Single<ThemeSwitcherComponent>
    val wearStatusComponent: Single<WearStatusComponent>
    val wearMessengerModule: WearMessengerModule
}
