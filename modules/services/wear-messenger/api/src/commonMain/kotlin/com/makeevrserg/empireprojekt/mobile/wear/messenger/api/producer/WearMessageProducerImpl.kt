package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer

import android.util.Log
import com.google.android.gms.wearable.MessageClient
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.message.WearMessage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalHorologistApi::class)
class WearMessageProducerImpl(
    private val wearDataLayerRegistry: WearDataLayerRegistry,
    private val messageClient: MessageClient,
) : WearMessageProducer {
    override suspend fun <T> produce(message: WearMessage<T>, value: T): Unit = coroutineScope {
        val nodes = wearDataLayerRegistry.nodeClient.connectedNodes.await()
        Log.d(TAG, "produce: found ${nodes.size} nodes")
        kotlin.runCatching {
            val byteArray = message.encode(value)
            nodes.map {
                async {
                    messageClient.sendMessage(
                        it.id,
                        message.path,
                        byteArray
                    )
                }
            }.awaitAll()
        }.onFailure {
            Log.e(TAG, "produce: failed to send message ${it.stackTraceToString()}")
        }.onSuccess {
            Log.d(TAG, "produce: message sent")
        }
    }

    companion object {
        private const val TAG = "WearMessageProducer"
    }
}
