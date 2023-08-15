package com.makeevrserg.empireprojekt.mobile.wear.di

import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher
import com.makeevrserg.empireprojekt.mobile.wear.di.impl.WearRootModuleImpl
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface WearRootModule : Module {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val settings: Single<Settings>
    val themeSwitcher: Single<ThemeSwitcher>
    val wearStatusComponent: Single<WearStatusComponent>

    companion object : WearRootModule by WearRootModuleImpl
}
