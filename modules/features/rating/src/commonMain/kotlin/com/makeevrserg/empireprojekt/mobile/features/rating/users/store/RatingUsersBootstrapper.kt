package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Action
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class RatingUsersBootstrapper(
    module: RatingUsersModule
) : CoroutineBootstrapper<Action>(),
    RatingUsersModule by module {

    override fun invoke() {
        collectPagingState()
        collectListStateFlow()
        updateRequestModel()
    }

    private fun updateRequestModel() {
        Action.UpdateRequestModel.run(::dispatch)
    }

    private fun collectPagingState() = scope.launch {
        ratingUsersRepository.pagingStateFlow.collectLatest {
            Action.PagingStateChanged(it).run(::dispatch)
        }
    }

    private fun collectListStateFlow() = scope.launch {
        ratingUsersRepository.listStateFlow.collectLatest {
            Action.ListChanged(it).run(::dispatch)
        }
    }
}
