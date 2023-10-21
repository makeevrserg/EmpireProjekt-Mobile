package com.makeevrserg.empireprojekt.mobile.features.rating.users

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStoreFactory
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val moduleFactory: Factory<RatingUsersModule>,
    private val onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
) : RatingUsersComponent,
    ComponentContext by componentContext {

    private val module = instanceKeeper.getOrCreate {
        moduleFactory.create()
    }

    private val store = instanceKeeper.getStore {
        RatingUsersStoreFactory(
            storeFactory = DefaultStoreFactory(),
            module = module
        ).create()
    }

    override val model = store.stateFlow.mapStateFlow {
        Model(
            items = it.items,
            request = it.request,
            isLoading = it.isLoading,
            isFailure = it.isFailure,
            isLastPage = it.isLastPage
        )
    }

    override fun showUserRatings(id: Long, userName: String) {
        onShowUserRatingsClicked.invoke(id, userName)
    }

    override fun reset() {
        RatingUsersStore.Intent.Reset.run(store::accept)
    }

    override fun loadNextPage() {
        RatingUsersStore.Intent.LoadNextPage.run(store::accept)
    }
}
