package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.receiver.WearMessageReceiver
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultPingComponent(
    componentContext: ComponentContext,
    private val wearMessageReceiver: WearMessageReceiver,
    private val wearMessageProducer: WearMessageProducer
) : ComponentContext by componentContext, PingComponent {
    private val pingFeature = instanceKeeper.getOrCreate {
        PingFeature(
            wearMessageReceiver = wearMessageReceiver,
            wearMessageProducer = wearMessageProducer
        )
    }
    override val model: StateFlow<PingComponent.Model> = pingFeature.model.asStateFlow()
}
