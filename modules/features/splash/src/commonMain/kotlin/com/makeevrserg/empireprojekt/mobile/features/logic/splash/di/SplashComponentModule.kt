package com.makeevrserg.empireprojekt.mobile.features.logic.splash.di

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.data.SplashComponentRepository
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.di.Module
import kotlinx.coroutines.CoroutineScope

interface SplashComponentModule : Module {
    val scope: CoroutineScope
    val dispatchers: KotlinDispatchers
    val repository: SplashComponentRepository
}
