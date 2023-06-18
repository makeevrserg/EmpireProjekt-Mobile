package com.makeevrserg.empireprojekt.mobile.features.status

import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.StateFlow

interface StatusComponent {
    val model: AnyStateFlow<Model>

    data class Model(
        val title: StringDesc,
        val isLoading: Boolean,
        val status: LoadingStatus = LoadingStatus.LOADING
    ) {
        enum class LoadingStatus {
            LOADING, SUCCESS, ERROR
        }
    }
}
