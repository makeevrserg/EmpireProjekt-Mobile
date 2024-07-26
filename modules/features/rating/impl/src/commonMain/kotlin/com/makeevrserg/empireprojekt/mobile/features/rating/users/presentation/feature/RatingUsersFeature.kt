package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.feature

import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.IntPagerCollector
import ru.astrainteractive.klibs.paging.PagingCollectorExt.resetAndLoadNextPage
import ru.astrainteractive.klibs.paging.data.CoroutineHandledPagedListDataSource
import kotlin.time.Duration.Companion.milliseconds

internal class RatingUsersFeature(
    dependencies: RatingUsersDependencies,
    private val filterProvider: FilterProvider
) : CoroutineFeature by CoroutineFeature.Main(),
    RatingUsersDependencies by dependencies {

    private val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = CoroutineHandledPagedListDataSource { pagingState ->
            ratingUsersRepository.fetchUsers(
                page = pagingState.pageContext.page,
                filterModel = filterProvider.filterStateFlow.value
            ).getOrThrow().data
        },
    )

    fun loadNextPage() {
        launch { pagingCollector.loadNextPage() }
    }

    fun reset() {
        launch { pagingCollector.resetAndLoadNextPage() }
    }

    val state = pagingCollector.state

    init {
        filterProvider.filterStateFlow
            .debounce(300.milliseconds)
            .onEach {
                pagingCollector.resetAndJoin()
                pagingCollector.loadNextPage()
            }.launchIn(this)
    }

    interface FilterProvider {
        val filterStateFlow: StateFlow<RatingsFilterModel>
    }
}
