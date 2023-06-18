package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.mobilex.core.platform.DefaultAndroidPlatformConfiguration
import com.makeevrserg.mobilex.di.getValue

class App : Application() {
    private val servicesModule by ServicesModule

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
