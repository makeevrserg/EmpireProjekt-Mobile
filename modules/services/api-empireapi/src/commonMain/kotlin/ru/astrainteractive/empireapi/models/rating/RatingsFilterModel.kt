package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

@Serializable
data class RatingsFilterModel(
    val nameSort: LocalSortOrder = LocalSortOrder.NONE,
    val lastUpdatedSort: LocalSortOrder = LocalSortOrder.NONE,
    val ratingSort: LocalSortOrder = LocalSortOrder.NONE,
    val query: String = "",
)
