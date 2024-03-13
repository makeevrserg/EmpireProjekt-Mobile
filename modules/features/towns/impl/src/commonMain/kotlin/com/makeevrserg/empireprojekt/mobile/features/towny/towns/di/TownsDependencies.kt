package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.TownsRepository
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.TownsRepositoryImpl
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal interface TownsDependencies {
    val townsRepository: TownsRepository

    class Default(
        apiEmpireApiModule: ApiEmpireApiModule,
        dispatchers: KotlinDispatchers,
        settings: Settings
    ) : TownsDependencies {
        override val townsRepository: TownsRepository by lazy {
            TownsRepositoryImpl(
                townyApi = apiEmpireApiModule.townyApi,
                dispatchers = dispatchers,
                settings = settings
            )
        }
    }
}
