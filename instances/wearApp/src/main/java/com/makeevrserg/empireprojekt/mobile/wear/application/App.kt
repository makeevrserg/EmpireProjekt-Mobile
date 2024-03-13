package com.makeevrserg.empireprojekt.mobile.wear.application

import android.app.Application
import android.content.Context
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    val wearRootModule by lazy {
        WearRootModule.Default()
    }

    override fun onCreate() {
        super.onCreate()
        wearRootModule.coreModule.platformConfiguration.initialize {
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
