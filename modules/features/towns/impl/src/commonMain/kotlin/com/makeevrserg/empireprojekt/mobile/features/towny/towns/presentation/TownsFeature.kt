package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.PagingCollectorExt.resetAndLoadNextPage

internal class TownsFeature(
    dependencies: TownsDependencies
) : CoroutineFeature by CoroutineFeature.Main(),
    TownsDependencies by dependencies {

    fun loadNextPage() {
        launch { townsRepository.pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch { townsRepository.pagingCollector.resetAndLoadNextPage() }
    }

    fun updateFilter(block: (TownsFilterModel) -> TownsFilterModel) = launch {
        townsRepository.updateFilter(block)
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
