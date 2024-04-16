package com.makeevrserg.empireprojekt.mobile.wear.di.impl

import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.status.DefaultWearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature

class WearRootModuleImpl : WearRootModule {

    override val coreModule: CoreModule by lazy {
        CoreModule.Default()
    }

    override val wearMessengerModule: WearMessengerModule by Single {
        WearMessengerModule.Default(
            context = coreModule.platformConfiguration.value.applicationContext,
            coroutineScope = CoroutineFeature.Main(),
        )
    }

    override val themeSwitcherModule: ThemeSwitcherModule by lazy {
        ThemeSwitcherModule.Default(coreModule = coreModule)
    }

    override val wearStatusComponent: Single<WearStatusComponent> = Single {
        DefaultWearStatusComponent(
            wearMessageConsumer = wearMessengerModule.wearMessageConsumer,
            coroutineScope = coreModule.mainScope
        )
    }
}
