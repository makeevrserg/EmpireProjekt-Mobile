package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.TownModel

interface TownsComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    data class Model(
        val items: List<TownModel> = emptyList(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
