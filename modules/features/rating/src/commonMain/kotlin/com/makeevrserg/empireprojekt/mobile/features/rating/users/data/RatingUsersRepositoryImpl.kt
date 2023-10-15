package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import com.makeevrserg.mobilex.paging.PagingCollector
import com.makeevrserg.mobilex.paging.data.LambdaPagedListDataSource
import com.makeevrserg.mobilex.paging.state.IntPagingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

class RatingUsersRepositoryImpl(
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUsersRepository {
    private val request = MutableStateFlow(RatingListRequest())

    private val pagingCollector = PagingCollector(
        initialPagingState = IntPagingState(0),
        pager = LambdaPagedListDataSource {
            loadPage(it.page)
        }
    )

    override val pagingStateFlow = pagingCollector.pagingStateFlow

    override val listStateFlow = pagingCollector.listStateFlow

    private suspend fun loadPage(page: Int): List<RatingUserModel>? {
        val result = runCatching {
            withContext(dispatchers.IO) {
                ratingApi.users(
                    page = page,
                    size = 10,
                    body = request.value
                ).data
            }
        }.onFailure(Throwable::printStackTrace)
        return result.getOrNull()
    }

    override suspend fun reset() {
        pagingCollector.reset()
    }

    override suspend fun loadNextPage() {
        pagingCollector.loadNextPage()
    }

    override fun updateRequest(request: RatingListRequest) {
        this.request.update {
            request
        }
    }
}
