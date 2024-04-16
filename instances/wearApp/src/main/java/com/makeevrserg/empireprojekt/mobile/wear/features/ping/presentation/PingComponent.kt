package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import kotlinx.coroutines.flow.StateFlow

interface PingComponent {
    val model: StateFlow<Model>

    sealed interface Model {
        data class Success(val updatedAt: String = "...") : Model

        data class NoConnection(val updatedAt: String = "...") : Model
    }
}
