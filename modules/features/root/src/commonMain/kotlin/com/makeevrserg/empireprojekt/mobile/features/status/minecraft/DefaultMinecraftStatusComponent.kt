package com.makeevrserg.empireprojekt.mobile.features.status.minecraft

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import com.makeevrserg.empireprojekt.mobile.services.core.wrapToAny
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultMinecraftStatusComponent(
    private val module: StatusModule,
    title: String,
    private val coroutineFeature: CoroutineFeature
) : StatusComponent, StatusModule by module {
    private val _model = MutableStateFlow(
        MinecraftStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = true,
            status = StatusComponent.Model.LoadingStatus.LOADING,
            statusResult = Result.failure(Throwable(""))
        )
    )
    override val model: AnyStateFlow<out StatusComponent.Model> = _model.wrapToAny()

    override fun checkStatus() {
        coroutineFeature.launch {
            checkOnce(false)
        }
    }

    override suspend fun checkOnce(force: Boolean) {
        if (_model.value.isLoading && !force) return
        _model.update {
            it.copy(isLoading = true)
        }
        val response = minecraftStatusRepository.get()
        _model.update {
            it.copy(
                isLoading = false,
                statusResult = response,
                status = when {
                    response.isSuccess -> StatusComponent.Model.LoadingStatus.SUCCESS
                    else -> StatusComponent.Model.LoadingStatus.ERROR
                },
            )
        }
    }

    private suspend fun setStatus() {
        checkOnce(true)
        delay(DELAY)
        setStatus()
    }

    init {
        coroutineFeature.launch { setStatus() }
    }

    companion object {
        private const val DELAY = 30000L
    }
}
