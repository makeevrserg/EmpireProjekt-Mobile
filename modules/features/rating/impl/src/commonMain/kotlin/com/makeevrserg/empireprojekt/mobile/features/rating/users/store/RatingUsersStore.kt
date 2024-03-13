package com.makeevrserg.empireprojekt.mobile.features.rating.users.store

import com.arkivanov.mvikotlin.core.store.Store
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.Label
import com.makeevrserg.empireprojekt.mobile.features.rating.users.store.RatingUsersStore.State
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.klibs.paging.context.IntPageContext
import ru.astrainteractive.klibs.paging.state.PagingState

internal interface RatingUsersStore : Store<Intent, State, Label> {
    data class State(
        val items: List<RatingUserModel> = emptyList(),
        val request: RatingListRequest = RatingListRequest(),
        val isLoading: Boolean = false,
        val isFailure: Boolean = false,
        val isLastPage: Boolean = false
    )

    sealed interface Intent {
        data object Reset : Intent
        data object LoadNextPage : Intent
    }

    sealed interface Message {
        class ListChanged(val list: List<RatingUserModel>) : Message
        class PagingStateChanged(val pagingState: PagingState<RatingUserModel, IntPageContext>) : Message
    }

    object Label

    sealed interface Action {
        class ListChanged(val list: List<RatingUserModel>) : Action
        class PagingStateChanged(val pagingState: PagingState<RatingUserModel, IntPageContext>) : Action
        data object UpdateRequestModel : Action
    }
}
