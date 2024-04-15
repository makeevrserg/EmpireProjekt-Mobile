package com.makeevrserg.empireprojekt.mobile.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.root.RootScreen
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

class MainActivity : ComponentActivity() {
    private val wearRootModule by Provider {
        application.asEmpireApp().wearRootModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(R.style.AppTheme)
        val rootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            wearRootModule = wearRootModule
        )
        setContent {
            ApplicationTheme(wearRootModule.themeSwitcherComponent.value) {
                RootScreen(rootComponent = rootComponent)
            }
        }
    }
}
