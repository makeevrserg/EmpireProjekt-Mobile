package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.paging.RatingsPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.rating.users.storage.RatingsFilterStorageValue
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.PagingCollectorExt.updatePageContext
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource

internal class RatingUsersRepositoryImpl(
    private val settings: Settings,
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUsersRepository {
    private val townsFilterStorageValue = RatingsFilterStorageValue(
        settings = settings,
        key = "ratings_filter_storage_key"
    )
    private var currentJob: Job? = null

    private suspend fun loadPage(
        page: Int,
        filter: RatingsFilterModel
    ): Result<List<RatingUserModel>> = coroutineScope {
        currentJob?.cancelAndJoin()
        currentJob = this.coroutineContext.job
        runCatching {
            withContext(dispatchers.IO) {
                ratingApi.users(
                    page = page,
                    size = 10,
                    body = filter
                ).data
            }
        }.onFailure(Throwable::printStackTrace)
    }

    override val pagingCollector = RatingsPagingCollector(
        initialPage = 0,
        pager = LambdaPagedListDataSource {
            loadPage(it.pageContext.page, it.pageContext.filter)
        },
        initialFilter = townsFilterStorageValue.value
    )

    override suspend fun updateFilter(buildFilter: (RatingsFilterModel) -> RatingsFilterModel) {
        val newFilter = buildFilter.invoke(pagingCollector.state.value.pageContext.filter)
        townsFilterStorageValue.save(newFilter)
        currentJob?.cancelAndJoin()
        pagingCollector.reset()
        pagingCollector.updatePageContext { it.copy(filter = newFilter) }
        pagingCollector.loadNextPage()
    }
}
