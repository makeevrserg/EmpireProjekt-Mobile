package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable

@Serializable
class UserRatingsRequest(
    val id: Long,
    val page: Long = 0,
    val size: Int = 54
)
