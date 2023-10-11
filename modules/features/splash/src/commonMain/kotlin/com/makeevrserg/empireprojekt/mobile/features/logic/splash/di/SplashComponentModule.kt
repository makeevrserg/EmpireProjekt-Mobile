package com.makeevrserg.empireprojekt.mobile.features.logic.splash.di

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.data.SplashComponentRepository
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.data.SplashComponentRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface SplashComponentModule : Module {
    val mainScope: CoroutineScope
    val dispatchers: KotlinDispatchers
    val repository: SplashComponentRepository

    class Default(
        override val mainScope: CoroutineScope,
        override val dispatchers: KotlinDispatchers
    ) : SplashComponentModule {
        override val repository: SplashComponentRepository = Provider {
            SplashComponentRepositoryImpl()
        }.provide()
    }
}
