package com.makeevrserg.empireprojekt.mobile.features.rating.user.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.DefaultRatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class RatingUserModuleImpl(
    apiEmpireApiModule: ApiEmpireApiModule,
    coreModule: CoreModule
) : RatingUserModule {
    private val ratingUserRepository by lazy {
        RatingUserRepositoryImpl(
            ratingApi = apiEmpireApiModule.ratingApi,
            dispatchers = coreModule.dispatchers
        )
    }

    override fun createRatingUserComponent(
        componentContext: ComponentContext,
        userId: Long,
        userName: String
    ): RatingUserComponent {
        return DefaultRatingUserComponent(
            componentContext = componentContext,
            userId = userId,
            userName = userName,
            ratingUserRepository = ratingUserRepository
        )
    }
}
