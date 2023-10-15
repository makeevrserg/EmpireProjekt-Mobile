package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.mobilex.paging.state.PagingState
import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel

interface RatingUsersRepository {
    fun updateRequest(request: RatingListRequest)
    suspend fun loadNextPage()
    suspend fun reset()
    val pagingStateFlow: MutableStateFlow<PagingState<Int>>
    val listStateFlow: MutableStateFlow<List<RatingUserModel>>
}
