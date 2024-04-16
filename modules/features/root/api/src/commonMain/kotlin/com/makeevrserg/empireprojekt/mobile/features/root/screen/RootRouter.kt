package com.makeevrserg.empireprojekt.mobile.features.root.screen

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface RootRouter {
    fun push(configuration: Configuration)
    fun replaceCurrent(configuration: Configuration)
    fun replaceAll(configuration: Configuration)

    sealed interface Configuration : Parcelable {
        @Parcelize
        data object Splash : Configuration

        @Parcelize
        data object Pager : Configuration

        @Parcelize
        class RatingUser(val userId: Long, val userName: String) : Configuration
    }
}
