package ru.astrainteractive.empireapi.models.towny

import kotlinx.serialization.Serializable

@Serializable
data class TownsFilter(
    val query: String = "",
    val publicType: TownPublicType = TownPublicType.NONE,
    val sortBy: TownSortBy = TownSortBy.NONE
)
