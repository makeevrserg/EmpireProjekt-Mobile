package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsDependencies
import kotlinx.coroutines.flow.StateFlow

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
}
