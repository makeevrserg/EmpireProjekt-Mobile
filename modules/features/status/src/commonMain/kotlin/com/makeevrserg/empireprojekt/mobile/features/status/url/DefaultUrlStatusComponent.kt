package com.makeevrserg.empireprojekt.mobile.features.status.url

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStoreFactory
import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import com.makeevrserg.empireprojekt.mobile.services.core.wrapToAny
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

class DefaultUrlStatusComponent(
    title: String,
    storeFactory: StoreFactory,
    urlStatusRepository: UrlStatusRepository
) : UrlStatusComponent {
    private val store = UrlStatusStoreFactory(storeFactory, urlStatusRepository).create()

    override val model: AnyStateFlow<UrlStatusComponent.Model> = store.stateFlow.mapStateFlow {
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
    }.wrapToAny()

    override fun checkStatus() {
        UrlStatusStore.Intent.CheckOnce(true).run(store::accept)
    }
}
