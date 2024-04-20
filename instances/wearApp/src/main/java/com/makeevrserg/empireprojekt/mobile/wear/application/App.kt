package com.makeevrserg.empireprojekt.mobile.wear.application

import android.app.Application
import android.content.Context
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.application.WearMessengerApplication
import com.makeevrserg.empireprojekt.mobile.wear.messenger.di.WearMessengerModule
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application(), WearMessengerApplication {
    val wearRootModule by lazy {
        WearRootModule.Default()
    }

    override val wearMessengerModule: WearMessengerModule
        get() = wearRootModule.wearMessengerModule

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
