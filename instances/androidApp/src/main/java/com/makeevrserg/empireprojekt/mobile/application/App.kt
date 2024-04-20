package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import android.content.Context
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import kotlinx.coroutines.cancel
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

@OptIn(ExperimentalHorologistApi::class)
class App : Application() {
    val rootModule by lazy {
        RootModule.Default()
    }
    val wearMessengerModule by lazy {
        WearMessengerModule.Default(
            context = rootModule.coreModule.platformConfiguration.value.applicationContext,
            coroutineScope = rootModule.coreModule.mainScope,
        )
    }
    private val wearDataLayerRegistry by lazy {
        WearDataLayerRegistry.fromContext(
            application = applicationContext,
            coroutineScope = rootModule.coreModule.mainScope
        )
    }
    private val messageClient by lazy {
        wearDataLayerRegistry.messageClient
    }

    override fun onTerminate() {
        super.onTerminate()
        rootModule.coreModule.mainScope.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        rootModule.coreModule.platformConfiguration.initialize {
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        }
    }

    companion object {
        fun Application.asEmpireApp(): App = (this as App)
        fun Context.asEmpireApp(): App = (applicationContext as App)
    }
}
