package com.makeevrserg.empireprojekt.mobile.application

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.mobilex.core.platform.DefaultAndroidPlatformConfiguration
import com.makeevrserg.mobilex.di.getValue

class App : Application() {
    private val rootModule by RootModule

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        rootModule.platformConfiguration.initialize(
            DefaultAndroidPlatformConfiguration(
                applicationContext
            )
        )
    }
}
