package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Action
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Label
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Message
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.State
import kotlinx.coroutines.launch

internal class RatingUsersExecutor(
    module: RatingUsersModule
) : CoroutineExecutor<Intent, Action, State, Message, Label>(),
    RatingUsersModule by module {
    override fun executeIntent(intent: Intent, getState: () -> State) {
        when (intent) {
            Intent.LoadNextPage -> scope.launch {
                ratingUsersRepository.loadNextPage()
            }

            Intent.Reset -> scope.launch {
                ratingUsersRepository.reset()
                ratingUsersRepository.loadNextPage()
            }
        }
    }

    override fun executeAction(action: Action, getState: () -> State) {
        when (action) {
            is Action.ListChanged -> {
                Message.ListChanged(action.list).run(::dispatch)
            }

            is Action.PagingStateChanged -> {
                Message.PagingStateChanged(action.pagingState).run(::dispatch)
            }

            Action.UpdateRequestModel -> {
                val request = getState.invoke().request
                ratingUsersRepository.updateRequest(request)
            }
        }
    }
}
