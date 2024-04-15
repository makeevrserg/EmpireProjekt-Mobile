package com.makeevrserg.empireprojekt.mobile.wear.features.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun openStatuses()

    fun openPing()

    fun pop()

    sealed interface Child {
        data object Ping : Child
        class Main(val themeSwitcherComponent: ThemeSwitcherComponent) : Child
        class Statuses(val wearStatusComponent: WearStatusComponent) : Child
    }
}
