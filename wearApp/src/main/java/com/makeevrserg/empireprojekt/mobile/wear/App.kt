package com.makeevrserg.empireprojekt.mobile.wear

import android.app.Application
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        WearRootModule.platformConfiguration.initialize(
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        )
    }
}
