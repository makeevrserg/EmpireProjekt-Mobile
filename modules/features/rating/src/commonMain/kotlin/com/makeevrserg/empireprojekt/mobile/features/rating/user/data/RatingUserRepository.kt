package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import com.makeevrserg.mobilex.paging.state.PagingState
import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest

interface RatingUserRepository {

    val pagingStateFlow: MutableStateFlow<PagingState<Int>>

    val listStateFlow: MutableStateFlow<List<RatingModel>>

    suspend fun reset()

    suspend fun loadNextPage()

    fun updateRequest(request: UserRatingsRequest)
}
