package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

class PingFeature(
    private val wearMessageConsumer: WearMessageConsumer,
    private val wearMessageProducer: WearMessageProducer
) : CoroutineFeature by CoroutineFeature.Default() {
    val model =
        MutableStateFlow<PingComponent.Model>(PingComponent.Model.NoConnection(getTimeStamp()))

    private fun getTimeStamp(): String {
        val lDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = lDateTime.format(formatter)
        return formatted ?: "..."
    }

    init {
        model
            .debounce(10.seconds)
            .onEach {
                if (model.value is PingComponent.Model.NoConnection) return@onEach
                model.value = PingComponent.Model.NoConnection(getTimeStamp())
            }.launchIn(this)

        wearMessageConsumer.messagesFlow
            .filterIsInstance<DecodedWearMessage<Byte>>()
            .onEach {
                val successModel = model.value as? PingComponent.Model.Success
                    ?: PingComponent.Model.Success(0)
                model.value = successModel.copy(
                    amount = successModel.amount + 1,
                    updatedAt = getTimeStamp()
                )
            }.launchIn(this)
    }
}
