package com.makeevrserg.empireprojekt.mobile.wear.features.main.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.wear.di.impl.WearRootModuleImpl
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.root.NavHostRootComponent
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

@Preview
@Composable
private fun RootScreenPreview() {
    val navController = rememberSwipeDismissableNavController()
    val navHostRootComponent = NavHostRootComponent(navController)
    val context = LocalContext.current
    val wearRootModule = WearRootModuleImpl().apply {
        platformConfiguration.initialize {
            DefaultAndroidPlatformConfiguration(context)
        }
    }
    ApplicationTheme {
        MainScreen(
            wearRootModule = wearRootModule,
            rootComponent = navHostRootComponent
        )
    }
}
