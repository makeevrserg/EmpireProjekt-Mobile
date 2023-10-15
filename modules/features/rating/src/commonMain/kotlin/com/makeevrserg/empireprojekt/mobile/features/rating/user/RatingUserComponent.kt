package com.makeevrserg.empireprojekt.mobile.features.rating.user

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest

interface RatingUserComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    data class Model(
        val items: List<RatingModel> = emptyList(),
        val request: UserRatingsRequest,
        val isLoading: Boolean = false,
        val isLastPage: Boolean = false,
        val isFailure: Boolean = false
    )
}
