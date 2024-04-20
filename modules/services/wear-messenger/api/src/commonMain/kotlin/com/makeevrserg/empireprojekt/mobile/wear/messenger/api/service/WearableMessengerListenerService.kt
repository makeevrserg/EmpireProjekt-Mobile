package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.service

import android.util.Log
import com.google.android.gms.wearable.WearableListenerService
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.application.WearMessengerApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class WearableMessengerListenerService : WearableListenerService() {
    @Suppress("VariableNaming")
    protected abstract val TAG: String

    protected val wearMessengerModule by lazy {
        (application as WearMessengerApplication).wearMessengerModule
    }

    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: DataLayerListenerService")
        scope.cancel()
    }
}
