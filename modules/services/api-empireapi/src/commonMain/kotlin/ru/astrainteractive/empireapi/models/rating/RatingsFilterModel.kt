package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

@Serializable
class RatingsFilterModel(
    val nameSort: LocalSortOrder? = null,
    val lastUpdatedSort: LocalSortOrder? = null,
    val ratingSort: LocalSortOrder? = null,
    val query: String = "",
)
