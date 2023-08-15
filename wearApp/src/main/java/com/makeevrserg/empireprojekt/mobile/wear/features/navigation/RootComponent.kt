package com.makeevrserg.empireprojekt.mobile.wear.features.navigation

interface RootComponent {
    fun openStatuses()
    sealed interface Child {
        object Main : Child
        object Statuses : Child
    }
}
