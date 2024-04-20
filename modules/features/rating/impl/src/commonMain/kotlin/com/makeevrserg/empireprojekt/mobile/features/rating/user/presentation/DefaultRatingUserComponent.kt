package com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository

internal class DefaultRatingUserComponent(
    componentContext: ComponentContext,
    userId: Long,
    userName: String,
    private val ratingUserRepository: RatingUserRepository
) : RatingUserComponent,
    ComponentContext by componentContext {
    private val ratingUserFeature = instanceKeeper.getOrCreate {
        RatingUserFeature(
            userId = userId,
            userName = userName,
            ratingUserRepository = ratingUserRepository
        )
    }

    override val model = ratingUserFeature.model

    override fun reset() {
        ratingUserFeature.reset()
    }

    override fun loadNextPage() {
        ratingUserFeature.loadNextPage()
    }
}
