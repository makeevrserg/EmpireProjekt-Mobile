package com.makeevrserg.empireprojekt.mobile.features.status

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.status.data.StatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.util.CoroutineFeature
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UrlStatusComponent(
    context: ComponentContext,
    url: String,
    title: String,
    module: StatusModule,
    coroutineFeature: CoroutineFeature
) : StatusComponent, StatusModule by module, ComponentContext by context {
    private val statusRepository: StatusRepository = UrlStatusRepository(
        url = url,
        httpClient = httpClient,
        dispatchers = dispatchers
    )

    override val model = MutableStateFlow(
        StatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = true
        )
    )

    private suspend fun setStatus() {
        model.update {
            it.copy(isLoading = true)
        }
        val response = statusRepository.isActive()
        response.onSuccess {
            model.update {
                it.copy(
                    status = StatusComponent.Model.LoadingStatus.SUCCESS,
                    isLoading = false
                )
            }
        }
        response.onFailure {
            model.update {
                it.copy(
                    status = StatusComponent.Model.LoadingStatus.ERROR,
                    isLoading = false
                )
            }
        }
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
