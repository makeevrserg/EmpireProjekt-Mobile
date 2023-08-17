package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.data.WearDataLayerRegistry
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import kotlinx.coroutines.MainScope
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
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
    }
}
