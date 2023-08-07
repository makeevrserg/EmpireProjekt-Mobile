package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    private val servicesModule by RootModule.servicesModule

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        servicesModule.platformConfiguration.initialize(
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        )
    }
}
