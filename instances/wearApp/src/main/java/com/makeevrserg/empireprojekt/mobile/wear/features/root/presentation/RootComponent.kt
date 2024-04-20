package com.makeevrserg.empireprojekt.mobile.wear.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation.PingComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun openStatuses()

    fun openPing()

    fun pop()

    sealed interface Child {
        class Ping(val pingComponent: PingComponent) : Child
        class Main(val themeSwitcherComponent: ThemeSwitcherComponent) : Child
        class Statuses(val wearStatusComponent: WearStatusComponent) : Child
    }
}
