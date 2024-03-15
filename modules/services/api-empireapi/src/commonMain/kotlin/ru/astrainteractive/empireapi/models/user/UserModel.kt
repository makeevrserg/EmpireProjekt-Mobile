package ru.astrainteractive.empireapi.models.user

import kotlinx.serialization.Serializable

@Serializable
class UserModel(
    val uuid: String,
    val nickname: String
)
