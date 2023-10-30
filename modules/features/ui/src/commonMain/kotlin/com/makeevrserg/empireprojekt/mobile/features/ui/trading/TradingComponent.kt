package com.makeevrserg.empireprojekt.mobile.features.ui.trading

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Instant

interface TradingComponent {
    val model: StateFlow<Model>

    data class Model(
        val entries: List<Entry> = emptyList()
    )

    data class Entry(
        val value: Float,
        val instant: Instant
    )
}