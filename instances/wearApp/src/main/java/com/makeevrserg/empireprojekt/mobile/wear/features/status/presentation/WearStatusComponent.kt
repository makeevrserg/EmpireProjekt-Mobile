package com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation

import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface WearStatusComponent {
    val mergedState: StateFlow<Model>
    data class Model(
        val loadingCount: Int = 0,
        val successCount: Int = 0,
        val failureCount: Int = 0,
        val updatedAt: String = "..."
    )

    fun update(status: UrlStatusComponent.LoadingStatus, amount: Int)

    class Stub : WearStatusComponent {
        private val mutableMergeState = MutableStateFlow(Model())
        override val mergedState: StateFlow<Model> = mutableMergeState
        override fun update(status: UrlStatusComponent.LoadingStatus, amount: Int) {
            mutableMergeState.update {
                when (status) {
                    UrlStatusComponent.LoadingStatus.LOADING -> it.copy(loadingCount = amount)
                    UrlStatusComponent.LoadingStatus.SUCCESS -> it.copy(successCount = amount)
                    UrlStatusComponent.LoadingStatus.ERROR -> it.copy(failureCount = amount)
                }
            }
        }
    }
}
