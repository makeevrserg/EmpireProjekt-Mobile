package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.receiver.WearMessageReceiver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PingFeature(
    private val wearMessageReceiver: WearMessageReceiver,
    private val wearMessageProducer: WearMessageProducer
) : CoroutineFeature by CoroutineFeature.Default() {
    val model = MutableStateFlow<PingComponent.Model>(PingComponent.Model.Pending)

    private fun getTimeStamp(): String {
        val lDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = lDateTime.format(formatter)
        return formatted ?: "..."
    }

    init {
        wearMessageReceiver.messagesFlow
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
