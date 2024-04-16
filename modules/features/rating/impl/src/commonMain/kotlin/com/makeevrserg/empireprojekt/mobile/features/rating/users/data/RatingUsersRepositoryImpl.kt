package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.paging.RatingsPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.rating.users.storage.RatingsFilterStorageValue
import com.russhwolf.settings.Settings
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.data.CoroutineHandledPagedListDataSource

internal class RatingUsersRepositoryImpl(
    private val settings: Settings,
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUsersRepository {
    private val townsFilterStorageValue = RatingsFilterStorageValue(
        settings = settings,
        key = "ratings_filter_storage_key"
    )

    override val pagingCollector = RatingsPagingCollector(
        initialPage = 0,
        pager = CoroutineHandledPagedListDataSource(dispatchers.IO) { pagingState ->
            ratingApi.users(
                page = pagingState.pageContext.page,
                size = 10,
                body = pagingState.pageContext.filter
            ).data
        },
        initialFilterFactory = { townsFilterStorageValue.value }
    )

    override suspend fun updateFilter(buildFilter: (RatingsFilterModel) -> RatingsFilterModel) {
        val newFilter = buildFilter.invoke(pagingCollector.state.value.pageContext.filter)
        townsFilterStorageValue.save(newFilter)
        pagingCollector.resetAndJoin()
        pagingCollector.loadNextPage()
    }
}
