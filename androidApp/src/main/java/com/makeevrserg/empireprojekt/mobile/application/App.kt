package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import android.util.Log
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    private val rootModule by RootModule
    private val servicesModule by RootModule.servicesModule

    @OptIn(ExperimentalHorologistApi::class)
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        servicesModule.platformConfiguration.initialize(
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        )
        val wearDataLayerRegistry = WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = MainScope()
        )
        // todo work manager
        val messageClient = wearDataLayerRegistry.messageClient

        MainScope().launch {
            while (isActive) {
                delay(5000L)
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
//                try {
//                    val helloWorld = "HelloWorld".toByteArray()
//                    // Send a message to all nodes in parallel
//                    nodes.map { node ->
//                        async {
//                            Log.d(TAG, "Sending message to: ${node.displayName}")
//                            messageClient.sendMessage(node.id, START_ACTIVITY_PATH, helloWorld)
//                                .await()
//                        }
//                    }.awaitAll()
//
//                    Log.d(TAG, "Starting activity requests sent successfully")
//                } catch (cancellationException: CancellationException) {
//                    throw cancellationException
//                } catch (exception: Exception) {
//                    Log.d(TAG, "Starting activity failed: $exception")
//                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
