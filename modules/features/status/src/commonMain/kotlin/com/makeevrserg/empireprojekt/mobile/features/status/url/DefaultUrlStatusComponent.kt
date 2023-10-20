package com.makeevrserg.empireprojekt.mobile.features.status.url

import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
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
    title: String,
    private val urlStatusRepository: UrlStatusRepository,
    private val coroutineFeature: CoroutineFeature
) : UrlStatusComponent {

    private val _model = MutableStateFlow(
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = true,
            status = UrlStatusComponent.LoadingStatus.LOADING
        )
    )
    override val model: AnyStateFlow<UrlStatusComponent.Model> = _model.wrapToAny()
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
        val response = urlStatusRepository.isActive()
        response.onSuccess {
            _model.update {
                it.copy(
                    status = UrlStatusComponent.LoadingStatus.SUCCESS,
                    isLoading = false
                )
            }
        }
        response.onFailure {
            _model.update {
                it.copy(
                    status = UrlStatusComponent.LoadingStatus.ERROR,
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
        private const val DELAY = 30000L
    }
}
