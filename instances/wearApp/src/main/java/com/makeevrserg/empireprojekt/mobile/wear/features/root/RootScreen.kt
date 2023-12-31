package com.makeevrserg.empireprojekt.mobile.wear.features.root

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.status.StatusesScreen

@Composable
fun RootScreen(
    rootComponent: NavHostRootComponent,
    wearRootModule: WearRootModule
) {
    SwipeDismissableNavHost(
        navController = rootComponent.navController,
        startDestination = RootComponent.Child.Main::class.simpleName!!
    ) {
        composable(RootComponent.Child.Main::class.simpleName!!) {
            MainScreen(wearRootModule = wearRootModule, rootComponent = rootComponent)
        }
        composable(RootComponent.Child.Statuses::class.simpleName!!) {
            StatusesScreen(wearRootModule.wearStatusComponent.value)
        }
    }
}
