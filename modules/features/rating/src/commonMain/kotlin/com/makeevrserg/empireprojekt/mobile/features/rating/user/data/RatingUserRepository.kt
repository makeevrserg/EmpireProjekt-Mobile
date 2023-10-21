package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.paging.state.PagingState

interface RatingUserRepository {

    val pagingStateFlow: StateFlow<PagingState<Int>>

    val listStateFlow: StateFlow<List<RatingModel>>

    suspend fun reset()

    suspend fun loadNextPage()

    fun updateRequest(request: UserRatingsRequest)
}
