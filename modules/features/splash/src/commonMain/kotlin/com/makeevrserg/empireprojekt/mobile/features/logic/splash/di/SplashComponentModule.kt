package com.makeevrserg.empireprojekt.mobile.features.logic.splash.di

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.data.SplashComponentRepository
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface SplashComponentModule : Module {
    val scope: CoroutineScope
    val dispatchers: KotlinDispatchers
    val repository: SplashComponentRepository
}
