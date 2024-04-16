package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation.InstanceKeeperExt.wrapInstanceKeeper
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.presentation.PingFeature
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class DefaultPingComponent(
    componentContext: ComponentContext,
    private val wearMessageConsumer: WearMessageConsumer,
    private val wearMessageProducer: WearMessageProducer
) : ComponentContext by componentContext, PingComponent {
    private val pingFeatureInstance = instanceKeeper.getOrCreate {
        PingFeature(
            wearMessageConsumer = wearMessageConsumer,
            wearMessageProducer = wearMessageProducer
        ).wrapInstanceKeeper { cancel() }
    }
    private val pingFeature: PingFeature
        get() = pingFeatureInstance.instance

    private fun getTimeStamp(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis)
        val lDateTime = LocalDateTime.ofInstant(
            instant,
            TimeZone.getDefault().toZoneId()
        )
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = lDateTime.format(formatter)
        return formatted ?: "..."
    }

    override val model: StateFlow<PingComponent.Model> = pingFeature.model.mapStateFlow {
        when (it) {
            is PingState.Connected -> PingComponent.Model.Success(
                updatedAt = getTimeStamp(it.timestamp)
            )

            is PingState.NotConnected -> PingComponent.Model.NoConnection(
                updatedAt = getTimeStamp(it.timestamp)
            )
        }
    }
}
