package com.makeevrserg.empireprojekt.mobile.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.features.root.NavHostRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.root.RootScreen
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

class MainActivity : ComponentActivity() {
    private val rootModule by Provider {
        application.asEmpireApp().wearRootModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(com.makeevrserg.empireprojekt.mobile.resources.R.style.AppTheme)
        setContent {
            val navController = rememberSwipeDismissableNavController()
            val navHostRootComponent = NavHostRootComponent(navController)
            ComposeApplication(rootModule.themeSwitcherComponent.value) {
                RootScreen(
                    rootComponent = navHostRootComponent,
                    wearRootModule = rootModule
                )
            }
        }
    }
}
