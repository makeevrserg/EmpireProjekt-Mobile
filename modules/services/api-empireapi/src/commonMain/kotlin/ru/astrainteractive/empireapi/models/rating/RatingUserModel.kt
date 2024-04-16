package ru.astrainteractive.empireapi.models.rating

import kotlinx.serialization.Serializable

@Serializable
data class RatingUserModel(
    val id: Long,
    val minecraftUUID: String,
    val minecraftName: String,
    val lastUpdated: Long,
    val totalRating: Long,
    val ratingVotes: Long,
)
