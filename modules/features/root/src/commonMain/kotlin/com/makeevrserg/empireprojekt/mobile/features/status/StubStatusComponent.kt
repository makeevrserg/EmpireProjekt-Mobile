package com.makeevrserg.empireprojekt.mobile.features.status

import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import com.makeevrserg.empireprojekt.mobile.services.core.wrapToAny
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StubStatusComponent : StatusComponent, CoroutineScope by MainScope() {
    private val mutableStateFlow = MutableStateFlow<StubModel>(StubModel())
    override val model: AnyStateFlow<StatusComponent.Model> = mutableStateFlow.wrapToAny()

    init {
        launch {
            while (isActive) {
                delay(1000L)
                checkStatus()
            }
        }
    }

    override fun checkStatus() {
        launch {
            checkOnce(false)
        }
    }

    override suspend fun checkOnce(force: Boolean) {
        mutableStateFlow.update {
            it.copy(isLoading = true)
        }
        delay(500L)
        mutableStateFlow.update {
            it.copy(
                isLoading = false,
                status = StatusComponent.Model.LoadingStatus.values().random()
            )
        }
    }

    private data class StubModel(
        override val title: StringDesc = StringDesc.Raw("Stub Title"),
        override val isLoading: Boolean = true,
        override val status: StatusComponent.Model.LoadingStatus = StatusComponent.Model.LoadingStatus.LOADING
    ) : StatusComponent.Model
}
