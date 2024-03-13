package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersDependencies
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Action
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class RatingUsersBootstrapper(
    dependencies: RatingUsersDependencies
) : CoroutineBootstrapper<Action>(),
    RatingUsersDependencies by dependencies {

    override fun invoke() {
        collectPagingState()
        updateRequestModel()
    }

    private fun updateRequestModel() {
        Action.UpdateRequestModel.run(::dispatch)
    }

    private fun collectPagingState() = scope.launch {
        ratingUsersRepository.state.collectLatest {
            Action.PagingStateChanged(it).run(::dispatch)
            Action.ListChanged(it.items).run(::dispatch)
        }
    }
}
