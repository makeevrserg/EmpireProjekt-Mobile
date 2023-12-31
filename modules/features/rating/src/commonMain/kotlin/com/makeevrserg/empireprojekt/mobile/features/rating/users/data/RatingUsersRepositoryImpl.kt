package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource

class RatingUsersRepositoryImpl(
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUsersRepository {
    private val request = MutableStateFlow(RatingListRequest())

    private val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = LambdaPagedListDataSource {
            loadPage(it.pageDescriptor)
        }
    )

    override val pagingStateFlow = pagingCollector.pagingStateFlow

    override val listStateFlow = pagingCollector.listStateFlow

    private suspend fun loadPage(page: Int): Result<List<RatingUserModel>> {
        return runCatching {
            withContext(dispatchers.IO) {
                ratingApi.users(
                    page = page,
                    size = 10,
                    body = request.value
                ).data
            }
        }.onFailure(Throwable::printStackTrace)
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
