package ru.astrainteractive.empireapi.models.towny

import kotlinx.serialization.Serializable

@Serializable
data class TownsFilterModel(
    val query: String = "",
    val publicType: TownPublicType = TownPublicType.NONE,
    val nameSort: LocalSortOrder = LocalSortOrder.NONE,
    val tagSort: LocalSortOrder = LocalSortOrder.NONE,
    val founderSort: LocalSortOrder = LocalSortOrder.NONE,
    val nationSort: LocalSortOrder = LocalSortOrder.NONE,
    val dateSort: LocalSortOrder = LocalSortOrder.NONE,
    val residentsSort: LocalSortOrder = LocalSortOrder.NONE
)
