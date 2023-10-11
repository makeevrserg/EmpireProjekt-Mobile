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
import com.makeevrserg.empireprojekt.mobile.work.CheckStatusWork
import kotlinx.coroutines.cancel
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalHorologistApi::class)
class App : Application() {
    val rootModule by lazy {
        RootModuleImpl()
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
        instanceWorkManager.enqueueUniquePeriodicWork(
            CheckStatusWork::class.java.simpleName,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            statusWork
        )
    }

    companion object {
        fun Application.asEmpireApp(): App = (this as App)
        fun Context.asEmpireApp(): App = (applicationContext as App)
    }
}
