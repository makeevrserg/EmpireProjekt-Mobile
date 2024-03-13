package com.makeevrserg.empireprojekt.mobile.features.splash.di

import com.makeevrserg.empireprojekt.mobile.features.splash.data.SplashComponentRepository
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal interface SplashComponentDependencies {
    val mainScope: CoroutineScope
    val dispatchers: KotlinDispatchers
    val repository: SplashComponentRepository

    class Default(
        override val mainScope: CoroutineScope,
        override val dispatchers: KotlinDispatchers,
        override val repository: SplashComponentRepository
    ) : SplashComponentDependencies
}
