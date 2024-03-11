package com.makeevrserg.empireprojekt.mobile.features.rating.user.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepositoryImpl
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface RatingUserModule {
    val ratingUserRepository: RatingUserRepository

    class Default(
        empireApiModule: EmpireApiModule,
        dispatchers: KotlinDispatchers
    ) : RatingUserModule {
        override val ratingUserRepository: RatingUserRepository by Single {
            RatingUserRepositoryImpl(
                ratingApi = empireApiModule.ratingApi,
                dispatchers = dispatchers
            )
        }
    }
}
