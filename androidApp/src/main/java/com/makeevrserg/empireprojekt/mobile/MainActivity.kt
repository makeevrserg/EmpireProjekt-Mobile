package com.makeevrserg.empireprojekt.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.core.ui.rememberSlotModalBottomSheetState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.modal.DefaultRootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.info.InfoScreen
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ApplicationContent
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
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
        val componentContext = defaultComponentContext()
        val rootComponent = DefaultRootComponent(componentContext, rootModule)
        val rootBottomSheetComponent = rootComponent.rootBottomSheetComponent

        setContent {
            val bottomSheetState = rememberSlotModalBottomSheetState(
                rootBottomSheetComponent.childSlot,
                onDismiss = rootBottomSheetComponent::dismissSlotChild
            ) { slotChild ->
                when (val child = slotChild.instance) {
                    is DefaultRootBottomSheetComponent.Configuration.SettingsChild -> {
                        InfoScreen(child.linkBrowser)
                    }
                }
            }
            ComposeApplication(rootModule.componentsModule.themeSwitcherComponent.value) {
                ModalBottomSheetLayout(
                    sheetState = bottomSheetState.sheetState,
                    sheetContent = bottomSheetState.sheetContent.value,
                    sheetShape = RoundedCornerShape(AppTheme.dimens.S),
                    sheetBackgroundColor = AppTheme.materialColor.primary
                ) {
                    ApplicationContent(
                        rootComponent = rootComponent,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
