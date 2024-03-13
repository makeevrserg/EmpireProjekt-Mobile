package com.makeevrserg.empireprojekt.mobile.features.rating.users.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.ApiEmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepository
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface RatingUsersModule {
    fun createRatingUsersComponent(
        componentContext: ComponentContext,
        onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
    ): RatingUsersComponent

    class Default(
        apiEmpireApiModule: ApiEmpireApiModule,
        coreModule: CoreModule
    ) : RatingUsersModule {
        private val ratingUsersRepository: RatingUsersRepository by lazy {
            RatingUsersRepositoryImpl(
                ratingApi = apiEmpireApiModule.ratingApi,
                dispatchers = coreModule.dispatchers
            )
        }

        override fun createRatingUsersComponent(
            componentContext: ComponentContext,
            onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
        ): RatingUsersComponent {
            val dependencies = RatingUsersDependencies.Default(
                ratingUsersRepository = ratingUsersRepository
            )
            return DefaultRatingUsersComponent(
                componentContext = componentContext,
                onShowUserRatingsClicked = onShowUserRatingsClicked,
                dependencies = dependencies
            )
        }
    }
}
