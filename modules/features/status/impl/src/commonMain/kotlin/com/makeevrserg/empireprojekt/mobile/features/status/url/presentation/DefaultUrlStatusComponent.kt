package com.makeevrserg.empireprojekt.mobile.features.status.url.presentation

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStoreFactory
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

internal class DefaultUrlStatusComponent(
    title: String,
    storeFactory: StoreFactory,
    urlStatusRepository: UrlStatusRepository
) : UrlStatusComponent {
    private val store = UrlStatusStoreFactory(storeFactory, urlStatusRepository).create()

    override val model: StateFlow<UrlStatusComponent.Model> = store.stateFlow.mapStateFlow {
        val status = when (it) {
            UrlStatusStore.State.LOADING -> UrlStatusComponent.LoadingStatus.LOADING
            UrlStatusStore.State.SUCCESS -> UrlStatusComponent.LoadingStatus.SUCCESS
            UrlStatusStore.State.ERROR -> UrlStatusComponent.LoadingStatus.ERROR
        }
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = status == UrlStatusComponent.LoadingStatus.LOADING,
            status = status
        )
    }

    override fun checkStatus() {
        UrlStatusStore.Intent.CheckOnce(true).run(store::accept)
    }
}
