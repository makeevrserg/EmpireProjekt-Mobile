package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import kotlinx.coroutines.flow.StateFlow

interface PingComponent {
    val model: StateFlow<Model>

    sealed interface Model {
        data object Pending : Model
        data class Success(
            val amount: Int,
            val updatedAt: String = "..."
        ) : Model

        data class Fail(val amount: Int) : Model
    }
}
