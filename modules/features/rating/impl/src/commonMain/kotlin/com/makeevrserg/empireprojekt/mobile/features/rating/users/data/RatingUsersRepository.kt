package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.paging.context.IntPageContext
import ru.astrainteractive.klibs.paging.state.PagingState

internal interface RatingUsersRepository {
    fun updateRequest(request: RatingsFilterModel)
    suspend fun loadNextPage()
    suspend fun reset()
    val state: StateFlow<PagingState<RatingUserModel, IntPageContext>>
}
