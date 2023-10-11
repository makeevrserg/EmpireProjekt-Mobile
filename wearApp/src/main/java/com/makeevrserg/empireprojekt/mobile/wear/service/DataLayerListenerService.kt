package com.makeevrserg.empireprojekt.mobile.wear.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.wear.application.App.Companion.asEmpireApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DataLayerListenerService : WearableListenerService() {
    private val wearRootModule by lazy {
        application.asEmpireApp().wearRootModule
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Log.d(TAG, "onMessageReceived: ${messageEvent.path}")
        scope.launch { receiveStatusModelMessage(messageEvent) }
    }

    private suspend fun receiveStatusModelMessage(messageEvent: MessageEvent) {
        val statusModelMessage = wearRootModule.wearMessengerModule.statusModelMessage
        val wearMessageReceiver = wearRootModule.wearMessengerModule.wearMessageReceiver
        if (statusModelMessage.path != messageEvent.path) return
        wearMessageReceiver.consume(
            message = statusModelMessage,
            byteArray = messageEvent.data
        )
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
        private const val TAG = "DataLayerService"
    }
}
