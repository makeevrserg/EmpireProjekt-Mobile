package com.makeevrserg.empireprojekt.mobile.wear.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.StubStatusComponent
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.combineStates

interface WearStatusComponent {
    val statuses: List<StatusComponent>
    val mergedState: StateFlow<Model>

    data class Model(
        val loadingCount: Int = 0,
        val successCount: Int = 0,
        val failureCount: Int = 0
    )

    class Stub : WearStatusComponent {
        override val statuses: List<StatusComponent> = List(10) {
            StubStatusComponent()
        }
        override val mergedState: StateFlow<Model> = combineStates(
            *statuses.map { it.model }.toTypedArray(),
            transform = { statuses ->
                val associated = statuses.map {
                    if (it.isLoading) {
                        StatusComponent.Model.LoadingStatus.LOADING
                    } else {
                        it.status
                    }
                }
                Model(
                    loadingCount = associated.count { it == StatusComponent.Model.LoadingStatus.LOADING },
                    successCount = associated.count { it == StatusComponent.Model.LoadingStatus.SUCCESS },
                    failureCount = associated.count { it == StatusComponent.Model.LoadingStatus.ERROR }
                )
            }
        )
    }
}
