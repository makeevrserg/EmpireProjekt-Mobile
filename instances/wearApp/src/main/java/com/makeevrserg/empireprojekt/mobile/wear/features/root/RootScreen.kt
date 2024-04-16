package com.makeevrserg.empireprojekt.mobile.wear.features.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.ui.PingScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.status.StatusesScreen

@Composable
fun RootScreen(
    rootComponent: RootComponent,
) {
    val child by rootComponent.childStack.subscribeAsState()
    val instance = child.active.instance
//    SwipeToDismissBox(stack = rootComponent.childStack, onDismissed = rootComponent::pop) { child ->
    when (instance) {
        is RootComponent.Child.Main -> MainScreen(
            themeSwitcherComponent =
            instance.themeSwitcherComponent,
            onOpenStatusesClicked = rootComponent::openStatuses,
            onOpenPingClicked = rootComponent::openPing
        )

        is RootComponent.Child.Ping -> PingScreen(
            pingComponent = instance.pingComponent
        )

        is RootComponent.Child.Statuses -> StatusesScreen(
            wearStatusComponent = instance.wearStatusComponent
        )
    }
//    }
}
