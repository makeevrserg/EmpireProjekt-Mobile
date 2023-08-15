package com.makeevrserg.empireprojekt.mobile.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.navigation.NavHostRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.navigation.NavigationScreen
import ru.astrainteractive.klibs.kdi.getValue

class MainActivity : ComponentActivity() {
    private val rootModule by WearRootModule
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(com.makeevrserg.empireprojekt.mobile.resources.R.style.AppTheme)
        setContent {
            val navController = rememberSwipeDismissableNavController()
            val navHostRootComponent = NavHostRootComponent(navController)
            ComposeApplication(rootModule.themeSwitcher.value) {
                NavigationScreen(navHostRootComponent)
            }
        }
    }
}
