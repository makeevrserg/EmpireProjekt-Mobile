package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.DefaultTownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface TownsModule {
    fun createTownsComponent(componentContext: ComponentContext): TownsComponent
    class Default(
        private val apiEmpireApiModule: ApiEmpireApiModule,
        private val coreModule: CoreModule
    ) : TownsModule {
        override fun createTownsComponent(componentContext: ComponentContext): TownsComponent {
            val dependencies = TownsDependencies.Default(
                apiEmpireApiModule = apiEmpireApiModule,
                dispatchers = coreModule.dispatchers,
                settings = coreModule.settings
            )
            return DefaultTownsComponent(
                componentContext = componentContext,
                dependencies = dependencies
            )
        }
    }
}
