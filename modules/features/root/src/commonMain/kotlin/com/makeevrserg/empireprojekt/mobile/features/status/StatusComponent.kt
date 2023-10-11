package com.makeevrserg.empireprojekt.mobile.features.status

import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import dev.icerock.moko.resources.desc.StringDesc

interface StatusComponent {
    val model: AnyStateFlow<out Model>
    fun checkStatus()
    suspend fun checkOnce(force: Boolean)
    interface Model {
        val title: StringDesc
        val isLoading: Boolean
        val status: LoadingStatus

        enum class LoadingStatus {
            LOADING, SUCCESS, ERROR
        }
    }
}
