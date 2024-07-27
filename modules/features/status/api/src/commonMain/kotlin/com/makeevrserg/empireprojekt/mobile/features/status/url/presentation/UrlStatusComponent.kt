package com.makeevrserg.empireprojekt.mobile.features.status.url.presentation

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.StateFlow

interface UrlStatusComponent {
    data class Model(
        val title: StringDesc,
        val isLoading: Boolean,
        val status: LoadingStatus
    )

    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }

    val model: StateFlow<Model>
    fun checkStatus()
}
