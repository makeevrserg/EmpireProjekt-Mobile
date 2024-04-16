package com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.application.WearMessengerApplication
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PingWearMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PingListenerService : WearableListenerService() {
    private val wearMessengerModule by lazy {
        println("GETTING wearMessengerModule: $application ${application as? WearMessengerApplication}")
        (application as WearMessengerApplication).wearMessengerModule
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receivePingMessage(messageEvent) }
    }

    private suspend fun receivePingMessage(messageEvent: MessageEvent) {
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
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: DataLayerListenerService")
        scope.cancel()
    }

    companion object {
        private const val TAG = "PingListenerService"
    }
}
