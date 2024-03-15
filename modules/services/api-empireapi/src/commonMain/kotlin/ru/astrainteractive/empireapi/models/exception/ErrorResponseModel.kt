package ru.astrainteractive.empireapi.models.exception

import kotlinx.serialization.Serializable

@Serializable
class ErrorResponseModel(val serverException: ServerException)
