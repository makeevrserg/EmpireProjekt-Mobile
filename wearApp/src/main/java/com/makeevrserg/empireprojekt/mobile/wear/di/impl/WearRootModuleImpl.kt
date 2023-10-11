package com.makeevrserg.empireprojekt.mobile.wear.di.impl

import com.makeevrserg.empireprojekt.mobile.features.root.di.factory.SettingsFactory
import com.makeevrserg.empireprojekt.mobile.features.theme.DefaultThemeSwitcherComponentComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.status.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

class WearRootModuleImpl : WearRootModule {
    override val platformConfiguration: Lateinit<PlatformConfiguration> = Lateinit()

    override val settings = Single {
        val configuration by platformConfiguration
        SettingsFactory(configuration).create()
    }
    private val mainScope by Single {
        MainScope()
    }

    override val jsonConfiguration = Single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    override val themeSwitcherComponent: Single<ThemeSwitcherComponent> = Single {
        val module = ThemeSwitcherModule.Default(settings.value)
        DefaultThemeSwitcherComponentComponent(module)
    }

    override val wearMessengerModule: WearMessengerModule by Single {
        WearMessengerModule.Default(
            context = platformConfiguration.value.applicationContext,
            coroutineScope = CoroutineFeature.Default(),
            json = jsonConfiguration.value
        )
    }

    override val wearStatusComponent: Single<WearStatusComponent> = Single {
        DefaultWearStatusComponent(
            wearMessageReceiver = wearMessengerModule.wearMessageReceiver,
            coroutineScope = mainScope
        )
    }
}
