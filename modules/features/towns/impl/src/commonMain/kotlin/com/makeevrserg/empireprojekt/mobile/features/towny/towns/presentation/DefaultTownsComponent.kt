package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy

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
        townsFeature.updateQuery(query)
    }

    override fun selectPublicType(townPublicType: TownPublicType) {
        townsFeature.selectPublicType(townPublicType)
    }

    override fun selectSortType(townSortBy: TownSortBy) {
        townsFeature.selectSortType(townSortBy)
    }
}
