package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable

@Serializable
class RatingModel(
    val userCreatedReport: RatingUserModel?,
    val reportedUser: RatingUserModel,
    val rating: Int,
    val ratingTypeIndex: Int,
    val message: String,
    val time: Long
)
