package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging.TownyPagingCollector
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.storage.TownsFilterStorageValue
import com.russhwolf.settings.Settings
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilter
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.PagingCollectorExt.submitList
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

    private suspend fun loadPage(page: Int, filter: TownsFilter): Result<List<TownModel>> {
        return runCatching {
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

    override suspend fun updateFilter(buildFilter: (TownsFilter) -> TownsFilter) {
        pagingCollector.submitList(emptyList())

        pagingCollector.update { state ->
            val newFilter = buildFilter.invoke(state.pageContext.filter)
            townsFilterStorageValue.save(newFilter)

            state.copy(
                pageContext = state.pageContext.copy(
                    page = 0,
                    filter = newFilter
                ),
                items = emptyList(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            )
        }
        pagingCollector.loadNextPage()
    }
}
