package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable

@Serializable
class RatingListRequest(
    val page: Long = 0,
    val size: Int = 10,
    val sort: Sort = Sort.NAME_ASC,
    val query: String = "",
) {
    enum class Sort {
        NAME_ASC, NAME_DESC
    }
}
