package ru.astrainteractive.empireapi.models.towny

import kotlinx.serialization.Serializable

@Serializable
data class NationModel(
    val name: String,
    val capital: String,
    val tag: String,
    val allies: List<String>,
    val enemies: List<String>,
    val registered: Long,
    val nationBoard: String,
    val isOpen: Boolean,
    val isPublic: Boolean
)
