package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.service.WearableMessengerListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.common.message.StatusModelMessage
import kotlinx.coroutines.launch

class StatusListenerService : WearableMessengerListenerService() {
    override val TAG: String = "StatusListenerService"

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receivePingMessage(messageEvent) }
    }

    private suspend fun receivePingMessage(messageEvent: MessageEvent) = kotlin.runCatching {
        val wearMessageReceiver = wearMessengerModule.wearMessageConsumer
        when (messageEvent.path) {
            StatusModelMessage.PATH -> wearMessageReceiver.consume(
                message = StatusModelMessage.Message,
                byteArray = messageEvent.data
            )

            else -> {
                Log.d(TAG, "receivePingMessage: can't handle message ${messageEvent.path}")
            }
        }
    }.onFailure(Throwable::printStackTrace)
}
