package com.makeevrserg.empireprojekt.mobile.features.rating.users.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.services.core.RetainedModule
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface RatingUsersModule : RetainedModule {
    val ratingUsersRepository: RatingUsersRepository

    class Default(
        empireApiModule: EmpireApiModule,
        dispatchers: KotlinDispatchers
    ) : RatingUsersModule {
        override val ratingUsersRepository: RatingUsersRepository by Single {
            RatingUsersRepositoryImpl(
                ratingApi = empireApiModule.ratingApi,
                dispatchers = dispatchers
            )
        }
    }
}
