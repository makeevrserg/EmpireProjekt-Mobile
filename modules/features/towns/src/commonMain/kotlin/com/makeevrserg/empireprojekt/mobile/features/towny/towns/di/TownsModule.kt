package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.DefaultTownsComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface TownsModule {
    fun createTownsComponent(componentContext: ComponentContext): TownsComponent
    class Default(
        private val empireApiModule: EmpireApiModule,
        private val dispatchers: KotlinDispatchers
    ) : TownsModule {
        override fun createTownsComponent(componentContext: ComponentContext): TownsComponent {
            val dependencies = TownsDependencies.Default(
                empireApiModule = empireApiModule,
                dispatchers = dispatchers
            )
            return DefaultTownsComponent(
                componentContext = componentContext,
                dependencies = dependencies
            )
        }
    }
}
