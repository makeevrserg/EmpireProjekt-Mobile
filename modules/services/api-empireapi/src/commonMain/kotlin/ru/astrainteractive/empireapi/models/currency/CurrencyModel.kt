package ru.astrainteractive.empireapi.models.currency

import kotlinx.serialization.Serializable

@Serializable
class CurrencyModel(
    val id: Long,
    val name: String,
    val author: UserModel
)
