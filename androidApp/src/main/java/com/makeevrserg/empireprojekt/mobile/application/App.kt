package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature
import com.makeevrserg.empireprojekt.mobile.work.CheckStatusWork
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalHorologistApi::class)
class App : Application() {
    private val servicesModule by RootModule.servicesModule
    private val coroutineFeature = CoroutineFeature.Default()
    private val wearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = coroutineFeature
        )
    }
    private val messageClient by lazy {
        wearDataLayerRegistry.messageClient
    }

    override fun onTerminate() {
        super.onTerminate()
        coroutineFeature.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        servicesModule.platformConfiguration.initialize(
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        )
        scheduleWork()
    }

    private fun scheduleWork() {
        val statusWork = PeriodicWorkRequest.Builder(
            CheckStatusWork::class.java,
            15,
            TimeUnit.MINUTES
        ).build()
        val instanceWorkManager = WorkManager.getInstance(applicationContext)
        instanceWorkManager.enqueueUniquePeriodicWork(
            CheckStatusWork::class.java.simpleName,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            statusWork
        )
        coroutineFeature.launch {
            while (isActive) {
                delay(5000L)
                CheckStatusWork.sendMessageOnWear(
                    wearDataLayerRegistry = wearDataLayerRegistry,
                    rootModule = RootModule,
                    messageClient = messageClient
                )
            }
        }
    }
}
