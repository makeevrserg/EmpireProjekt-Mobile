package com.makeevrserg.empireprojekt.mobile.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.wearable.MessageClient
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import ru.astrainteractive.klibs.kdi.getValue

@OptIn(ExperimentalHorologistApi::class)
class CheckStatusWork(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val rootModule by RootModule
    private val rootStatusComponent by rootModule.rootStatusComponent

    override suspend fun doWork(): Result = coroutineScope {
        Log.d(TAG, "doWork: ")
        rootStatusComponent.statusComponents.map {
            async {
                it.checkStatus()
            }
        }.awaitAll()
        Result.success()
    }

    companion object {
        private const val TAG = "CheckStatusWork"
        suspend fun sendMessageOnWear(
            wearDataLayerRegistry: WearDataLayerRegistry,
            rootModule: RootModule,
            messageClient: MessageClient
        ) = coroutineScope {
            kotlin.runCatching {
                val nodes = wearDataLayerRegistry.nodeClient.connectedNodes.await()
                Log.d(TAG, "Contains ${nodes.size} nodes")
                val mapped = rootModule.rootStatusComponent.value.statusComponents.map {
                    if (it.model.value.isLoading) {
                        StatusComponent.Model.LoadingStatus.LOADING
                    } else {
                        it.model.value.status
                    }
                }
                val statuses = buildList {
                    StatusComponent.Model.LoadingStatus.SUCCESS.let { status ->
                        status to mapped.count { it == status }
                    }.run(::add)
                    StatusComponent.Model.LoadingStatus.ERROR.let { status ->
                        status to mapped.count { it == status }
                    }.run(::add)
                    StatusComponent.Model.LoadingStatus.LOADING.let { status ->
                        status to mapped.count { it == status }
                    }.run(::add)
                }
                nodes.flatMap { node ->
                    statuses.map { entry ->
                        async {
                            messageClient.sendMessage(
                                node.id,
                                "/statuses" + entry.first.name,
                                byteArrayOf(entry.second.toByte())
                            )
                        }
                    }
                }.awaitAll()
                Log.d(TAG, "Sended ")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}
