package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel

interface RatingUsersComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun nextLastUpdateSort()

    fun nextRatingSort()

    fun nextNameSort()

    fun showUserRatings(id: Long, userName: String)

    data class Model(
        val items: List<RatingUserModel> = emptyList(),
        val filter: RatingsFilterModel = RatingsFilterModel(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false,
    )
}
