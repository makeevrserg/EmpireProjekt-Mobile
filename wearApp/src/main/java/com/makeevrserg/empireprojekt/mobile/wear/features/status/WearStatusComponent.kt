package com.makeevrserg.empireprojekt.mobile.wear.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface WearStatusComponent {
    val mergedState: StateFlow<Model>

    data class Model(
        val loadingCount: Int = 0,
        val successCount: Int = 0,
        val failureCount: Int = 0
    )

    fun update(status: StatusComponent.Model.LoadingStatus, amount: Int)

    class Stub : WearStatusComponent {
        private val mutableMergeState = MutableStateFlow(Model())
        override val mergedState: StateFlow<Model> = mutableMergeState
        override fun update(status: StatusComponent.Model.LoadingStatus, amount: Int) {
            mutableMergeState.update {
                when (status) {
                    StatusComponent.Model.LoadingStatus.LOADING -> it.copy(loadingCount = amount)
                    StatusComponent.Model.LoadingStatus.SUCCESS -> it.copy(successCount = amount)
                    StatusComponent.Model.LoadingStatus.ERROR -> it.copy(failureCount = amount)
                }
            }
        }
    }
}
