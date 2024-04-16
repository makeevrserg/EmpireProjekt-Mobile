package ru.astrainteractive.empireapi.models.exception

import kotlinx.serialization.Serializable

@Serializable
sealed class ServerException : Throwable()
