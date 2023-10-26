package com.makeevrserg.empireprojekt.mobile.features.status.url

import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import dev.icerock.moko.resources.desc.StringDesc

interface UrlStatusComponent {
    data class Model(
        val title: StringDesc,
        val isLoading: Boolean,
        val status: LoadingStatus
    )

    enum class LoadingStatus {
        LOADING, SUCCESS, ERROR
    }

    val model: AnyStateFlow<Model>
    fun checkStatus()
}
