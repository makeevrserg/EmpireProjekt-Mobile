package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent.Model
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.klibs.mikro.core.util.next

internal class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val dependencies: RatingUsersDependencies,
    private val onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
) : RatingUsersComponent,
    ComponentContext by componentContext {
    private val ratingUsersFeature = instanceKeeper.getOrCreate {
        RatingUsersFeature(dependencies = dependencies)
    }
    override val model: StateFlow<Model>
        get() = ratingUsersFeature.model

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
        ratingUsersFeature.updateFilter { it.copy(query = query) }
    }

    override fun nextLastUpdateSort() {
        ratingUsersFeature.updateFilter { it.copy(lastUpdatedSort = it.lastUpdatedSort.next()) }
    }

    override fun nextRatingSort() {
        ratingUsersFeature.updateFilter { it.copy(ratingSort = it.ratingSort.next()) }
    }

    override fun nextNameSort() {
        ratingUsersFeature.updateFilter { it.copy(nameSort = it.nameSort.next()) }
    }

    override fun showUserRatings(id: Long, userName: String) {
        onShowUserRatingsClicked.invoke(id, userName)
    }
}
