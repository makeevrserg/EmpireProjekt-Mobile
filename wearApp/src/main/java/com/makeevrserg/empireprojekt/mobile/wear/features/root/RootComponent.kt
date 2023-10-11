package com.makeevrserg.empireprojekt.mobile.wear.features.root

interface RootComponent {
    fun openStatuses()
    sealed interface Child {
        data object Main : Child
        data object Statuses : Child
    }
}
