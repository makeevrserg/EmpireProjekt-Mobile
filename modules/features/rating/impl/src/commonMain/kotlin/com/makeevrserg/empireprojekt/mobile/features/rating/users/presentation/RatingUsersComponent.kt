package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel

interface RatingUsersComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun showUserRatings(id: Long, userName: String)

    data class Model(
        val items: List<RatingUserModel> = emptyList(),
        val request: RatingsFilterModel = RatingsFilterModel(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
