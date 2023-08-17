package com.makeevrserg.empireprojekt.mobile.wear.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class DataLayerListenerService : WearableListenerService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)

        kotlin.runCatching {
            val statusRaw = messageEvent.path.replace("/statuses", "")
            val status = StatusComponent.Model.LoadingStatus.valueOf(statusRaw)
            val amount = messageEvent.data.first().toInt()
            WearRootModule.wearStatusComponent.value.update(status, amount)
            Log.d("DataLayerService", "loadingStatus: $status; amount: $amount")
        }.onFailure {
            it.printStackTrace()
        }
        kotlin.runCatching {
            Log.d(TAG, "onMessageReceived: ${messageEvent.data.decodeToString()}")
        }.onFailure { it.printStackTrace() }
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
