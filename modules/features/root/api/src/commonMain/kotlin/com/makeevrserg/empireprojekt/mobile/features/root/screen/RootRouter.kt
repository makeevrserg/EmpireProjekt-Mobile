package com.makeevrserg.empireprojekt.mobile.features.root.screen

import kotlinx.serialization.Serializable

interface RootRouter {
    fun push(configuration: Configuration)
    fun replaceCurrent(configuration: Configuration)
    fun replaceAll(configuration: Configuration)

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Splash : Configuration

        @Serializable
        data object Pager : Configuration

        @Serializable
        class RatingUser(val userId: Long, val userName: String) : Configuration
    }
}
