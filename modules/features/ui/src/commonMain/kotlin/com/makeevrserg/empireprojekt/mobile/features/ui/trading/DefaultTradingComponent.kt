package com.makeevrserg.empireprojekt.mobile.features.ui.trading

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class DefaultTradingComponent(
    componentContext: ComponentContext
) : TradingComponent,
    ComponentContext by componentContext {
    override val model = MutableStateFlow(TradingComponent.Model())

    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }
    private val randomValueFlow = flow<TradingComponent.Entry> {
        while (currentCoroutineContext().isActive) {
            delay(1.seconds)
            val entry = TradingComponent.Entry(
                value = Random.nextDouble(10.0, 13.0).toFloat(),
                instant = Clock.System.now()
            )
            emit(entry)
        }
    }

    init {
        model.update {
            val entries = List(5) {
                TradingComponent.Entry(
                    value = Random.nextDouble(10.0, 13.0).toFloat(),
                    instant = Clock.System.now().minus((5 - it).seconds)
                )
            }
            it.copy(entries = entries)
        }
        coroutineFeature.launch {
            randomValueFlow.collectLatest { entry ->
                model.update { model -> model.copy(entries = model.entries + entry) }
            }
        }
    }
}