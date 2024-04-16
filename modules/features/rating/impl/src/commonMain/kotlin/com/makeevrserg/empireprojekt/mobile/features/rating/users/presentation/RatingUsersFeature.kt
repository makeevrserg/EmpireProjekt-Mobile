package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.PagingCollectorExt.resetAndLoadNextPage

internal class RatingUsersFeature(
    dependencies: RatingUsersDependencies
) : CoroutineFeature by CoroutineFeature.Main(),
    RatingUsersDependencies by dependencies {

    fun loadNextPage() {
        launch { ratingUsersRepository.pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch { ratingUsersRepository.pagingCollector.resetAndLoadNextPage() }
    }

    fun updateFilter(block: (RatingsFilterModel) -> RatingsFilterModel) = launch {
        ratingUsersRepository.updateFilter(block)
    }

    val model = ratingUsersRepository.pagingCollector.state.mapStateFlow {
        RatingUsersComponent.Model(
            items = it.items,
            isLoading = it.isLoading,
            isFailure = it.isFailure,
            isLastPage = it.isLastPage,
            filter = it.pageContext.filter
        )
    }
}
