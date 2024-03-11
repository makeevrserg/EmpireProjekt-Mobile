package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.TownsRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.TownsRepositoryImpl
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal interface TownsDependencies {
    val townsRepository: TownsRepository

    class Default(
        empireApiModule: EmpireApiModule,
        dispatchers: KotlinDispatchers,
        settings: Settings
    ) : TownsDependencies {
        override val townsRepository: TownsRepository by lazy {
            TownsRepositoryImpl(
                townyApi = empireApiModule.townyApi,
                dispatchers = dispatchers,
                settings = settings
            )
        }
    }
}
