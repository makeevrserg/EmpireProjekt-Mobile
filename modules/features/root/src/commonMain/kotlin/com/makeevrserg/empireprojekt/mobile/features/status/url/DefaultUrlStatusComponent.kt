package com.makeevrserg.empireprojekt.mobile.features.status.url

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.data.StatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.data.UrlStatusRepository
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

class DefaultUrlStatusComponent(
    context: ComponentContext,
    url: String,
    title: String,
    module: StatusModule,
    private val coroutineFeature: CoroutineFeature
) : UrlStatusComponent, StatusModule by module, ComponentContext by context {
    private val statusRepository: StatusRepository = UrlStatusRepository(
        url = url,
        httpClient = httpClient,
        dispatchers = dispatchers
    )

    private val _model = MutableStateFlow(
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = true,
            status = StatusComponent.Model.LoadingStatus.LOADING
        )
    )
    override val model: AnyStateFlow<StatusComponent.Model> = _model.wrapToAny()
    override fun checkStatus() {
        coroutineFeature.launch {
            checkOnce(false)
        }
    }

    private suspend fun checkOnce(force: Boolean) {
        if (_model.value.isLoading && !force) return
        _model.update {
            it.copy(isLoading = true)
        }
        val response = statusRepository.isActive()
        response.onSuccess {
            _model.update {
                it.copy(
                    status = StatusComponent.Model.LoadingStatus.SUCCESS,
                    isLoading = false
                )
            }
        }
        response.onFailure {
            _model.update {
                it.copy(
                    status = StatusComponent.Model.LoadingStatus.ERROR,
                    isLoading = false
                )
            }
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
        private const val DELAY = 5000L
    }
}
