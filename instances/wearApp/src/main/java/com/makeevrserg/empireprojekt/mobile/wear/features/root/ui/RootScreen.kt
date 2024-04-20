package com.makeevrserg.empireprojekt.mobile.wear.features.root.ui

import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.ui.PingScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.root.presentation.RootComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.ui.StatusesScreen
import com.makeevrserg.empireprojekt.mobile.wear.ui.SwipeToDismissBox

@Composable
fun RootScreen(
    rootComponent: RootComponent,
) {
    SwipeToDismissBox(stack = rootComponent.childStack, onDismissed = rootComponent::pop) { child ->
        when (val instance = child.instance) {
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
    }
}
