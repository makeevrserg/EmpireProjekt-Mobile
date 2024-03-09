package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy
import ru.astrainteractive.empireapi.models.towny.TownsFilter
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.PagingCollectorExt.submitList

internal class TownsFeature(
    dependencies: TownsDependencies
) : CoroutineFeature by CoroutineFeature.Main(),
    TownsDependencies by dependencies {

    fun loadNextPage() {
        launch { townsRepository.pagingCollector.loadNextPage() }
    }

    fun reset() {
        townsRepository.pagingCollector.reset()
    }

    private fun updateFilter(buildFilter: (TownsFilter) -> TownsFilter) {
        townsRepository.pagingCollector.submitList(emptyList())
        townsRepository.pagingCollector.update { state ->
            state.copy(
                pageContext = state.pageContext.copy(
                    page = 0,
                    filter = buildFilter.invoke(state.pageContext.filter)
                ),
                items = emptyList(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            )
        }
        loadNextPage()
    }

    fun updateQuery(query: String) {
        updateFilter { filter ->
            filter.copy(query = query)
        }
    }

    fun selectPublicType(townPublicType: TownPublicType) {
        updateFilter { filter ->
            filter.copy(publicType = townPublicType)
        }
    }

    fun selectSortType(townSortBy: TownSortBy) {
        updateFilter { filter ->
            filter.copy(sortBy = townSortBy)
        }
    }

    val model = townsRepository.pagingCollector.state.mapStateFlow {
        TownsComponent.Model(
            items = it.items,
            isLoading = it.isLoading,
            isFailure = it.isFailure,
            isLastPage = it.isLastPage,
            filter = it.pageContext.filter
        )
    }
}
