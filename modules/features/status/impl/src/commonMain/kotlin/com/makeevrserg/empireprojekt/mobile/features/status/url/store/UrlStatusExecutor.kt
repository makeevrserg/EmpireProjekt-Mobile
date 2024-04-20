package com.makeevrserg.empireprojekt.mobile.features.status.url.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Action
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Message
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.State
import kotlinx.coroutines.launch

internal class UrlStatusExecutor(
    private val urlStatusRepository: UrlStatusRepository
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.CheckOnce -> scope.launch {
                checkOnce(intent.force, state())
            }
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            is Action.CheckOnce -> scope.launch {
                checkOnce(action.force, state())
            }
        }
    }

    private suspend fun checkOnce(force: Boolean, state: State) {
        if (state == State.LOADING && !force) return
        Message.NowLoading.run(::dispatch)
        val response = urlStatusRepository.isActive().getOrNull() ?: false
        if (response) {
            Message.Available.run(::dispatch)
        } else {
            Message.UnAvailable.run(::dispatch)
        }
    }
}
