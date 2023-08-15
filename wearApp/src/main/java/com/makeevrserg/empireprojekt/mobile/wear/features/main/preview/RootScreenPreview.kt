package com.makeevrserg.empireprojekt.mobile.wear.features.main.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.navigation.NavHostRootComponent

@Preview
@Composable
private fun RootScreenPreview() {
    val navController = rememberSwipeDismissableNavController()
    val navHostRootComponent = NavHostRootComponent(navController)
    ComposeApplication {
        MainScreen(wearRootModule = WearRootModule, rootComponent = navHostRootComponent)
    }
}
