package ru.astrainteractive.empireapi.models.exception

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthException : ServerException() {
    @Serializable
    data object WrongPassword : AuthException()

    @Serializable
    data object UserNotExists : AuthException()

    @Serializable
    data object WrongBody : AuthException()

    @Serializable
    data object NotAuthorized : AuthException()
}
