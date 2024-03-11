package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Label
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.State
import ru.astrainteractive.klibs.kdi.Factory

internal class RatingUsersStoreFactory(
    private val storeFactory: StoreFactory,
    private val module: RatingUsersModule
) : Factory<RatingUsersStore> {

    override fun create(): RatingUsersStore {
        return RatingUsersStoreImpl()
    }

    private inner class RatingUsersStoreImpl :
        RatingUsersStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "RatingUsersStore",
            initialState = RatingUsersStore.State(),
            bootstrapper = RatingUsersBootstrapper(module),
            executorFactory = { RatingUsersExecutor(module) },
            reducer = RatingUsersReducer
        )
}
