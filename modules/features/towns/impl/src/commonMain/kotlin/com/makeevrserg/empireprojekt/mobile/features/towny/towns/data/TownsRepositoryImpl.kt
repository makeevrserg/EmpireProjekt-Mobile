package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging.TownyPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.storage.TownsFilterStorageValue
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.PagingCollectorExt.updatePageContext
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource

internal class TownsRepositoryImpl(
    private val townyApi: TownyApi,
    private val dispatchers: KotlinDispatchers,
    private val settings: Settings
) : TownsRepository {
    private val townsFilterStorageValue = TownsFilterStorageValue(
        settings = settings,
        key = "towns_filter_storage_key"
    )
    private var currentJob: Job? = null

    private suspend fun loadPage(page: Int, filter: TownsFilterModel): Result<List<TownModel>> = coroutineScope {
        currentJob?.cancelAndJoin()
        currentJob = this.coroutineContext.job
        runCatching {
            withContext(dispatchers.IO) {
                townyApi.towns(
                    page = page,
                    size = 10,
                    filter = filter
                ).data
            }
        }.onFailure(Throwable::printStackTrace)
    }

    override val pagingCollector = TownyPagingCollector(
        initialPage = 0,
        pager = LambdaPagedListDataSource {
            loadPage(it.pageContext.page, it.pageContext.filter)
        },
        initialFilter = townsFilterStorageValue.value
    )

    override suspend fun updateFilter(buildFilter: (TownsFilterModel) -> TownsFilterModel) {
        val newFilter = buildFilter.invoke(pagingCollector.state.value.pageContext.filter)
        townsFilterStorageValue.save(newFilter)
        currentJob?.cancelAndJoin()
        pagingCollector.reset()
        pagingCollector.updatePageContext { it.copy(filter = newFilter) }
        pagingCollector.loadNextPage()
    }
}
