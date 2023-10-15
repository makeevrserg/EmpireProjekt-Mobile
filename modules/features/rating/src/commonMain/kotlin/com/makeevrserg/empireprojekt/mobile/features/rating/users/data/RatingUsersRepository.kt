package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.mobilex.paging.state.PagingState
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel

interface RatingUsersRepository {
    fun updateRequest(request: RatingListRequest)
    suspend fun loadNextPage()
    suspend fun reset()
    val pagingStateFlow: StateFlow<PagingState<Int>>
    val listStateFlow: StateFlow<List<RatingUserModel>>
}
