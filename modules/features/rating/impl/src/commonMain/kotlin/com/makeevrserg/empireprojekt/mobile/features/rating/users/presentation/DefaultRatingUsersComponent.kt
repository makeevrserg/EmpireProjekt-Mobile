package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.feature.FilterFeature
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.feature.RatingUsersFeature
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.klibs.mikro.core.util.combineStates
import ru.astrainteractive.klibs.mikro.core.util.next

internal class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit,
    private val createRatingUsersFeature: (RatingUsersFeature.FilterProvider) -> RatingUsersFeature,
    private val createFilterFeature: () -> FilterFeature
) : RatingUsersComponent,
    ComponentContext by componentContext {
    private val filterFeature = instanceKeeper.getOrCreate {
        createFilterFeature.invoke()
    }
    private val ratingUsersFeature = instanceKeeper.getOrCreate {
        createRatingUsersFeature.invoke(filterFeature)
    }
    override val model: StateFlow<Model> = combineStates(
        flow1 = filterFeature.filterStateFlow,
        flow2 = ratingUsersFeature.state,
        transform = { filter, pagingState ->
            Model(
                items = pagingState.items,
                filter = filter,
                isLoading = pagingState.isLoading,
                isFailure = pagingState.isFailure,
                isLastPage = pagingState.isLastPage
            )
        }
    )

    private fun LocalSortOrder?.next(): LocalSortOrder {
        return this?.next(LocalSortOrder.entries.toTypedArray()) ?: LocalSortOrder.entries.first()
    }

    override fun reset() {
        ratingUsersFeature.reset()
    }

    override fun loadNextPage() {
        ratingUsersFeature.loadNextPage()
    }

    override fun updateQuery(query: String) {
        filterFeature.updateFilter { it.copy(query = query) }
    }

    override fun nextLastUpdateSort() {
        filterFeature.updateFilter { it.copy(lastUpdatedSort = it.lastUpdatedSort.next()) }
    }

    override fun nextRatingSort() {
        filterFeature.updateFilter { it.copy(ratingSort = it.ratingSort.next()) }
    }

    override fun nextNameSort() {
        filterFeature.updateFilter { it.copy(nameSort = it.nameSort.next()) }
    }

    override fun showUserRatings(id: Long, userName: String) {
        onShowUserRatingsClicked.invoke(id, userName)
    }
}
