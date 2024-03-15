package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel

interface TownsComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun nextPublicType()

    fun nextNameSort()

    fun nextTagSort()

    fun nextFounderSort()

    fun nextNationSort()

    fun nextDateSort()

    fun nextResidentsSort()

    data class Model(
        val items: List<TownModel> = emptyList(),
        val filter: TownsFilterModel,
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
