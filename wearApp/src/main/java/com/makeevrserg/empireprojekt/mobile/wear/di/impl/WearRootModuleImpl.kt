package com.makeevrserg.empireprojekt.mobile.wear.di.impl

import com.makeevrserg.empireprojekt.mobile.features.root.di.factories.SettingsFactory
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

object WearRootModuleImpl : WearRootModule {
    override val platformConfiguration: Lateinit<PlatformConfiguration> = Lateinit()
    override val settings = Single {
        val configuration by platformConfiguration
        SettingsFactory(configuration).create()
    }
    override val themeSwitcher: Single<ThemeSwitcher> = Single {
        ThemeSwitcherComponent(settings.value)
    }
    override val wearStatusComponent: Single<WearStatusComponent> = Single {
        WearStatusComponent.Stub()
    }
}
