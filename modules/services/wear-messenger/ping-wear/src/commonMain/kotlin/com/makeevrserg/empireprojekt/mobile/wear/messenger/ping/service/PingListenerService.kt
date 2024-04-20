package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.service.WearableMessengerListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PingWearMessage
import kotlinx.coroutines.launch

class PingListenerService : WearableMessengerListenerService() {
    override val TAG: String = "PingListenerService"

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receivePingMessage(messageEvent) }
    }

    private suspend fun receivePingMessage(messageEvent: MessageEvent) = kotlin.runCatching {
        val wearMessageReceiver = wearMessengerModule.wearMessageConsumer
        when (messageEvent.path) {
            PingWearMessage.PATH -> wearMessageReceiver.consume(
                message = PingWearMessage.Message,
                byteArray = messageEvent.data
            )

            else -> {
                Log.d(TAG, "receivePingMessage: can't handle message ${messageEvent.path}")
            }
        }
    }.onFailure(Throwable::printStackTrace)
}
