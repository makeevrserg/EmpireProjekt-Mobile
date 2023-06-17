package com.makeevrserg.empireprojekt.mobile.features.logic.splash.data

interface SplashComponentRepository {
    fun isInitialLaunch(): Boolean

    /**
     * Default implementation for this interface from [LocalPreferenceSource]
     */
    class Default : SplashComponentRepository {
        override fun isInitialLaunch(): Boolean {
            return true
        }
    }
}
