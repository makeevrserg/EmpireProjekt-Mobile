package com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy
import ru.astrainteractive.empireapi.models.towny.TownsFilter

interface TownsComponent {
    val model: StateFlow<Model>

    fun reset()

    fun loadNextPage()

    fun updateQuery(query: String)

    fun selectPublicType(townPublicType: TownPublicType)

    fun selectSortType(townSortBy: TownSortBy)

    data class Model(
        val items: List<TownModel> = emptyList(),
        val filter: TownsFilter,
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )
}
