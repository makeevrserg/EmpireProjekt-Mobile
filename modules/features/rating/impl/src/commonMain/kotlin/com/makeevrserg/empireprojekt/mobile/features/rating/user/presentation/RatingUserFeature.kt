package com.makeevrserg.empireprojekt.mobile.features.rating.user.presentation

import com.makeevrserg.empireprojekt.mobile.features.rating.user.data.RatingUserRepository
import kotlinx.coroutines.launch
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.CoroutineFeature
import ru.astrainteractive.klibs.paging.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.CoroutineHandledPagedListDataSource

internal class RatingUserFeature(
    private val userId: Long,
    userName: String,
    private val ratingUserRepository: RatingUserRepository
) : CoroutineFeature by CoroutineFeature.Main() {
    private val pagingCollector = IntPagerCollector(
        pager = CoroutineHandledPagedListDataSource { pagingState ->
            ratingUserRepository.getRatings(
                userId = userId,
                page = pagingState.pageContext.page,
                size = pagingState.pageSizeAtLeast
            )
        }
    )
    val model = pagingCollector.state.mapStateFlow {
        RatingUserComponent.Model(
            items = it.items,
            request = UserRatingsRequest(id = userId),
            reviewedUserName = userName,
            isLoading = it.isLoading,
            isLastPage = it.isLastPage,
            isFailure = it.isFailure
        )
    }

    fun reset() = launch {
        pagingCollector.resetAndJoin()
        pagingCollector.loadNextPage()
    }

    fun loadNextPage() = launch {
        pagingCollector.loadNextPage()
    }

    init {
        reset()
    }
}
