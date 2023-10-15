package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource

class RatingUserRepositoryImpl(
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUserRepository {
    private val request = MutableStateFlow(UserRatingsRequest(0))

    private val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = LambdaPagedListDataSource {
            loadPage(it.pageDescriptor)
        }
    )

    override val pagingStateFlow = pagingCollector.pagingStateFlow

    override val listStateFlow = pagingCollector.listStateFlow

    private suspend fun loadPage(page: Int): Result<List<RatingModel>> {
        return runCatching {
            withContext(dispatchers.IO) {
                ratingApi.ratings(
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
        println("LoadingNextPage in repository")
        println(pagingCollector.pagingStateFlow.value)
        pagingCollector.loadNextPage()
    }

    override fun updateRequest(request: UserRatingsRequest) {
        this.request.update {
            request
        }
    }
}
