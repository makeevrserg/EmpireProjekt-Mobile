package ru.astrainteractive.empireapi.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(val id: Long, val mail: String)
