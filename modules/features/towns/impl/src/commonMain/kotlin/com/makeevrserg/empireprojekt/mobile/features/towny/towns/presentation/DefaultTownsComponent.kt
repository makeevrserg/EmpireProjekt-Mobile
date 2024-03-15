package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.klibs.mikro.core.util.next

@Suppress("TooManyFunctions")
internal class DefaultTownsComponent(
    componentContext: ComponentContext,
    dependencies: TownsDependencies
) : TownsComponent, ComponentContext by componentContext {
    private val townsFeature = instanceKeeper.getOrCreate {
        TownsFeature(dependencies)
    }
    override val model: StateFlow<TownsComponent.Model>
        get() = townsFeature.model

    override fun reset() {
        townsFeature.reset()
    }

    override fun loadNextPage() {
        townsFeature.loadNextPage()
    }

    override fun updateQuery(query: String) {
        townsFeature.updateFilter { it.copy(query = query) }
    }

    private fun LocalSortOrder?.next(): LocalSortOrder {
        return this?.next(LocalSortOrder.entries.toTypedArray()) ?: LocalSortOrder.entries.first()
    }
    private fun TownPublicType?.next(): TownPublicType {
        return this?.next(TownPublicType.entries.toTypedArray()) ?: TownPublicType.entries.first()
    }

    override fun nextPublicType() {
        townsFeature.updateFilter { it.copy(publicType = it.publicType.next()) }
    }

    override fun nextNameSort() {
        townsFeature.updateFilter { it.copy(nameSort = it.nameSort.next()) }
    }

    override fun nextTagSort() {
        townsFeature.updateFilter { it.copy(tagSort = it.tagSort.next()) }
    }

    override fun nextFounderSort() {
        townsFeature.updateFilter { it.copy(founderSort = it.founderSort.next()) }
    }

    override fun nextNationSort() {
        townsFeature.updateFilter { it.copy(nationSort = it.nationSort.next()) }
    }

    override fun nextDateSort() {
        townsFeature.updateFilter { it.copy(dateSort = it.dateSort.next()) }
    }

    override fun nextResidentsSort() {
        townsFeature.updateFilter { it.copy(residentsSort = it.residentsSort.next()) }
    }
}
