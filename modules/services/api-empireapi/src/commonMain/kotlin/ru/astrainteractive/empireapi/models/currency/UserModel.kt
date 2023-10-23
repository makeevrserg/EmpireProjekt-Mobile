package ru.astrainteractive.empireapi.models.currency

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Long,
    val username: String
)
