package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.consumer

import android.util.Log
import com.google.android.gms.wearable.MessageClient
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.DecodedWearMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalHorologistApi::class)
@Suppress("UnusedPrivateMember")
class WearMessageConsumerImpl(
    private val wearDataLayerRegistry: WearDataLayerRegistry,
    private val messageClient: MessageClient,
) : WearMessageConsumer {
    private val messageChannel = Channel<DecodedWearMessage<*>>()
    override val messagesFlow: Flow<DecodedWearMessage<*>> = messageChannel.receiveAsFlow()

    override suspend fun <T> consume(message: WearMessage<T>, byteArray: ByteArray) {
        kotlin.runCatching {
            val decodedWearMessage = DecodedWearMessage(
                path = message.path,
                value = message.decode(byteArray)
            )
            messageChannel.send(decodedWearMessage)
        }.onFailure {
            Log.d(TAG, "consume: could not publish message: ${it.stackTraceToString()}")
        }.onSuccess {
            Log.d(TAG, "consume: published message")
        }
    }

    companion object {
        private const val TAG = "WearMessageReceiver"
    }
}
