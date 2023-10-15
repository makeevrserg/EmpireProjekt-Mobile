package com.makeevrserg.empireprojekt.mobile.features.rating.users

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel

interface RatingUsersComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    data class Model(
        val items: List<RatingUserModel> = emptyList(),
        val request: RatingListRequest = RatingListRequest(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
