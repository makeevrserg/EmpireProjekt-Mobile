package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.klibs.paging.context.IntPageContext
import ru.astrainteractive.klibs.paging.state.PagingState

interface RatingUsersRepository {
    fun updateRequest(request: RatingListRequest)
    suspend fun loadNextPage()
    suspend fun reset()
    val state: StateFlow<PagingState<RatingUserModel, IntPageContext>>
}
