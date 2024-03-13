package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.paging.context.IntPageContext
import ru.astrainteractive.klibs.paging.state.PagingState

internal interface RatingUserRepository {

    val state: StateFlow<PagingState<RatingModel, IntPageContext>>

    suspend fun reset()

    suspend fun loadNextPage()

    fun updateRequest(request: UserRatingsRequest)
}
