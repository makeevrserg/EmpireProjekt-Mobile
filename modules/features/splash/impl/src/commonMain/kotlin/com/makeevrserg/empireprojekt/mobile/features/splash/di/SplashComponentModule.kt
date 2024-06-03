package com.makeevrserg.empireprojekt.mobile.features.splash.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.splash.data.SplashComponentRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.splash.presentation.DefaultSplashComponent
import com.makeevrserg.empireprojekt.mobile.features.splash.presentation.SplashComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface SplashComponentModule {
    fun createSplashComponent(componentContext: ComponentContext): SplashComponent

    class Default(
        private val coreModule: CoreModule
    ) : SplashComponentModule {
        override fun createSplashComponent(componentContext: ComponentContext): SplashComponent {
            return DefaultSplashComponent(
                context = componentContext,
                dependencies = SplashComponentDependencies.Default(
                    mainScope = coreModule.mainScope,
                    dispatchers = coreModule.dispatchers,
                    repository = SplashComponentRepositoryImpl()
                )
            )
        }
    }
}
