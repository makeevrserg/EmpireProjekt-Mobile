package com.makeevrserg.empireprojekt.mobile.features.status.url.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.State
import ru.astrainteractive.klibs.kdi.Factory

class UrlStatusStoreFactory(
    private val storeFactory: StoreFactory,
    private val urlStatusRepository: UrlStatusRepository
) : Factory<UrlStatusStore> {

    override fun create(): UrlStatusStore {
        return UrlStatusStoreImpl()
    }

    private inner class UrlStatusStoreImpl :
        UrlStatusStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "UrlStatusStore",
            initialState = UrlStatusStore.State.LOADING,
            bootstrapper = UrlStatusBootstrapper(),
            executorFactory = { UrlStatusExecutor(urlStatusRepository) },
            reducer = UrlStatusReducer
        )
}
