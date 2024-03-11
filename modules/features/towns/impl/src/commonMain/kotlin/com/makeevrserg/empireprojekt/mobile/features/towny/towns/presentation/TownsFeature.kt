package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature

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

    fun updateQuery(query: String) = launch {
        townsRepository.updateFilter { filter ->
            filter.copy(query = query)
        }
    }

    fun selectPublicType(townPublicType: TownPublicType) = launch {
        townsRepository.updateFilter { filter ->
            filter.copy(publicType = townPublicType)
        }
    }

    fun selectSortType(townSortBy: TownSortBy) = launch {
        townsRepository.updateFilter { filter ->
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
