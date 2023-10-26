package com.makeevrserg.empireprojekt.mobile.features.status.url.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Message
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.State

object UrlStatusReducer : Reducer<State, Message> {
    override fun State.reduce(msg: Message): State {
        return when (msg) {
            Message.Available -> State.SUCCESS
            Message.NowLoading -> State.LOADING
            Message.UnAvailable -> State.ERROR
        }
    }
}
