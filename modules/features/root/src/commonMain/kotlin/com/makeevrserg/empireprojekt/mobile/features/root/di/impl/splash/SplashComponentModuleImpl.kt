package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.splash

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.data.SplashComponentRepository
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

@Suppress("UnusedPrivateMember")
class SplashComponentModuleImpl(
    rootModule: RootModule,
    servicesModule: ServicesModule
) : SplashComponentModule {

    override val scope: CoroutineScope by rootModule.mainScope
    override val dispatchers: KotlinDispatchers by rootModule.dispatchers
    override val repository: SplashComponentRepository = Provider {
        SplashComponentRepository.Default()
    }.provide()
}
