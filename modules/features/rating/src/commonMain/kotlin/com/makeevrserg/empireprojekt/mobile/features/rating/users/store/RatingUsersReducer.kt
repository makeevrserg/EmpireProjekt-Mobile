package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Message
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.State

internal object RatingUsersReducer : Reducer<State, Message> {
    override fun State.reduce(msg: Message): State {
        return when (msg) {
            is Message.ListChanged -> {
                copy(items = msg.list)
            }

            is Message.PagingStateChanged -> copy(
                isLoading = msg.pagingState.isLoading,
                isFailure = msg.pagingState.isFailure,
                isLastPage = msg.pagingState.isLastPage
            )
        }
    }
}
