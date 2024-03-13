package com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent.Model
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStoreFactory
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

internal class DefaultRatingUsersComponent(
    componentContext: ComponentContext,
    private val dependencies: RatingUsersDependencies,
    private val onShowUserRatingsClicked: (userId: Long, userName: String) -> Unit
) : RatingUsersComponent,
    ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        RatingUsersStoreFactory(
            storeFactory = DefaultStoreFactory(),
            dependencies = dependencies
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
