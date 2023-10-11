package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.RootModuleImpl
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import com.makeevrserg.empireprojekt.mobile.work.CheckStatusWork
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalHorologistApi::class)
class App : Application() {
    val rootModule by lazy {
        RootModuleImpl()
    }
    val wearMessengerModule by lazy {
        WearMessengerModule.Default(
            context = rootModule.servicesModule.platformConfiguration.value.applicationContext,
            coroutineScope = rootModule.servicesModule.mainScope.value,
            json = rootModule.servicesModule.jsonConfiguration.value
        )
    }
    private val wearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = rootModule.servicesModule.mainScope.value
        )
    }
    private val messageClient by lazy {
        wearDataLayerRegistry.messageClient
    }

    override fun onTerminate() {
        super.onTerminate()
        rootModule.servicesModule.mainScope.value.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        rootModule.servicesModule.platformConfiguration.initialize {
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        }
        scheduleWork()
    }

    private fun scheduleWork() {
        val statusWork = PeriodicWorkRequest.Builder(
            CheckStatusWork::class.java,
            15,
            TimeUnit.MINUTES
        ).build()
        val instanceWorkManager = WorkManager.getInstance(applicationContext)

        rootModule.servicesModule.mainScope.value.launch {
            while (isActive) {
                instanceWorkManager.enqueueUniquePeriodicWork(
                    CheckStatusWork::class.java.simpleName,
                    ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                    statusWork
                )
                delay(30.seconds)
            }
        }
    }

    companion object {
        fun Application.asEmpireApp(): App = (this as App)
        fun Context.asEmpireApp(): App = (applicationContext as App)
    }
}
