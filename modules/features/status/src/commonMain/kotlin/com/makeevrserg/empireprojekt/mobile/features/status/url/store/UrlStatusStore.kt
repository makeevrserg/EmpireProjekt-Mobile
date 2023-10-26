package com.makeevrserg.empireprojekt.mobile.features.status.url.store

import com.arkivanov.mvikotlin.core.store.Store
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.State

internal interface UrlStatusStore : Store<Intent, State, Label> {
    enum class State {
        LOADING, SUCCESS, ERROR
    }

    sealed interface Intent {
        class CheckOnce(val force: Boolean) : Intent
    }

    sealed interface Message {
        data object NowLoading : Message
        data object Available : Message
        data object UnAvailable : Message
    }

    sealed interface Label

    sealed interface Action {
        class CheckOnce(val force: Boolean) : Action
    }
}
