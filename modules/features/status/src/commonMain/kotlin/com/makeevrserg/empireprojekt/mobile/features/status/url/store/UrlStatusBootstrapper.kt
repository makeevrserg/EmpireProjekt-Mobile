package com.makeevrserg.empireprojekt.mobile.features.status.url.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.makeevrserg.empireprojekt.mobile.features.status.url.store.UrlStatusStore.Action
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class UrlStatusBootstrapper : CoroutineBootstrapper<Action>() {
    override fun invoke() {
        startPeriodicCheck()
    }

    private fun startPeriodicCheck() = scope.launch {
        while (isActive) {
            Action.CheckOnce(force = true).run(::dispatch)
            delay(DELAY)
        }
    }

    companion object {
        private val DELAY = 30.seconds
    }
}
