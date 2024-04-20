package com.makeevrserg.empireprojekt.mobile.wear.di

import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.di.PingModule
import com.makeevrserg.empireprojekt.mobile.wear.features.status.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

interface WearRootModule : Module {
    val coreModule: CoreModule
    val themeSwitcherModule: ThemeSwitcherModule
    val wearStatusComponent: Single<WearStatusComponent>
    val wearMessengerModule: WearMessengerModule
    val pingModule: PingModule

    class Default : WearRootModule {
        override val coreModule: CoreModule by lazy {
            CoreModule.Default()
        }
        override val themeSwitcherModule: ThemeSwitcherModule by lazy {
            ThemeSwitcherModule.Default(coreModule = coreModule)
        }

        override val wearMessengerModule: WearMessengerModule by Single {
            WearMessengerModule.Default(
                context = coreModule.platformConfiguration.value.applicationContext,
                coroutineScope = coreModule.mainScope,
            )
        }

        override val wearStatusComponent: Single<WearStatusComponent> = Single {
            DefaultWearStatusComponent(
                wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
                coroutineScope = coreModule.mainScope
            )
        }

        override val pingModule: PingModule by lazy {
            PingModule.Default(
                wearMessengerModule = wearMessengerModule
            )
        }
    }
}
