package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model

import kotlinx.serialization.Serializable

@Serializable
class StatusModel(
    val title: String,
    val isLoading: Boolean,
    val status: LoadingStatus
) {
    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }
}
