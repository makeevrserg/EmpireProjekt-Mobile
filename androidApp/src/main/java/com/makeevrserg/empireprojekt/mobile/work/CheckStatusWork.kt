package com.makeevrserg.empireprojekt.mobile.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.message.StatusModelMessage
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.app.model.StatusModel
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.producer.WearMessageProducerImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

@OptIn(ExperimentalHorologistApi::class)
class CheckStatusWork(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val wearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = rootModule.servicesModule.mainScope.value
        )
    }
    private val messageClient by lazy {
        wearDataLayerRegistry.messageClient
    }
    private val wearMessageProducer by lazy {
        WearMessageProducerImpl(
            wearDataLayerRegistry = wearDataLayerRegistry,
            messageClient = messageClient
        )
    }

    private val rootModule by lazy {
        applicationContext.asEmpireApp().rootModule
    }
    private val rootStatusComponent by Provider {
        rootModule.rootStatusComponent.value
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: ")
        sendStatus()
        return Result.success()
    }

    private suspend fun sendStatus() = coroutineScope {
        val messages = rootStatusComponent.statusComponents.map {
            async {
                it.checkStatus()
                val model = it.model.value
                StatusModel(
                    title = model.title.toString(applicationContext),
                    isLoading = model.isLoading,
                    status = when (model.status) {
                        StatusComponent.Model.LoadingStatus.LOADING -> StatusModel.LoadingStatus.LOADING
                        StatusComponent.Model.LoadingStatus.SUCCESS -> StatusModel.LoadingStatus.SUCCESS
                        StatusComponent.Model.LoadingStatus.ERROR -> StatusModel.LoadingStatus.ERROR
                    }
                )
            }
        }.awaitAll()
        val statusModelMessage = StatusModelMessage(
            json = rootModule.servicesModule.jsonConfiguration.value
        )
        wearMessageProducer.produce(statusModelMessage, messages)
    }

    companion object {
        private const val TAG = "CheckStatusWork"
    }
}
