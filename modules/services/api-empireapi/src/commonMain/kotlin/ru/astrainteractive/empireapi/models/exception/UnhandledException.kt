package ru.astrainteractive.empireapi.models.exception

import kotlinx.serialization.Serializable

@Serializable
data object UnhandledException : ServerException()
