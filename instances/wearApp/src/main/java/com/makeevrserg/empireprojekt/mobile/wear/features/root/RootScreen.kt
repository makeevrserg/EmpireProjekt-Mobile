package com.makeevrserg.empireprojekt.mobile.wear.features.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.status.StatusesScreen
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
                onOpenStatusesClicked = rootComponent::openStatuses
            )

            RootComponent.Child.Ping -> TODO()
            is RootComponent.Child.Statuses -> StatusesScreen(
                wearStatusComponent = instance.wearStatusComponent
            )
        }
    }
}
