package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultPingComponent(
    componentContext: ComponentContext,
    private val wearMessageConsumer: WearMessageConsumer,
    private val wearMessageProducer: WearMessageProducer
) : ComponentContext by componentContext, PingComponent {
    private val pingFeature = instanceKeeper.getOrCreate {
        PingFeature(
            wearMessageConsumer = wearMessageConsumer,
            wearMessageProducer = wearMessageProducer
        )
    }
    override val model: StateFlow<PingComponent.Model> = pingFeature.model.asStateFlow()
}
