package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.presentation

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer.WearMessageConsumer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducer
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.model.PingState
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PingWearMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.Instant
import kotlin.time.Duration.Companion.milliseconds

@Suppress("UnusedPrivateMember")
class PingFeature(
    private val wearMessageConsumer: WearMessageConsumer,
    private val wearMessageProducer: WearMessageProducer
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    private val _model = MutableStateFlow<PingState>(PingState.NotConnected(getMillis()))
    val model = _model.asStateFlow()

    private fun getMillis(): Long = Instant.now().toEpochMilli()

    private fun launchDebounceJob() {
        _model
            .debounce(PingWearMessage.DEBOUNCE.milliseconds)
            .filterIsInstance<PingState.Connected>()
            .onEach { _model.value = PingState.NotConnected(getMillis()) }
            .launchIn(this)
    }

    private fun launchPingJob() {
        wearMessageConsumer.messagesFlow
            .filterIsInstance<DecodedWearMessage<Byte>>()
            .onEach { _model.value = PingState.Connected(getMillis()) }
            .launchIn(this)
    }

    init {
        launchPingJob()
        launchDebounceJob()
    }
}
