package com.makeevrserg.empireprojekt.mobile

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationContent
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.root.RootBottomSheetContent
import com.makeevrserg.empireprojekt.mobile.resources.R
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    private val rootModule by Provider {
        application.asEmpireApp().rootModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(R.style.AppTheme)
        if (Build.MANUFACTURER.contains("Redmi 7", true)) error("No application for you")
        if (Build.MODEL.contains("Redmi 7", true)) error("No application for you")
        if (Build.ID.contains("Redmi 7", true)) error("No application for you")

        if (Build.MANUFACTURER.contains("M1810F6", true)) error("No application for you")
        if (Build.MODEL.contains("M1810F6", true)) error("No application for you")
        if (Build.ID.contains("M1810F6", true)) error("No application for you")

        val componentContext = defaultComponentContext()
        val rootComponent = DefaultRootComponent(componentContext, rootModule)
        val rootBottomSheetComponent = rootComponent.rootBottomSheetComponent
        setContent {
            ApplicationTheme(rootModule.componentsModule.themeSwitcherComponent.value) {
                RootBottomSheetContent(rootBottomSheetComponent) {
                    ApplicationContent(
                        rootComponent = rootComponent,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
