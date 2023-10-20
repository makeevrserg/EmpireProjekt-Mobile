package com.makeevrserg.empireprojekt.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.core.ui.rememberDeclarativeModalBottomSheetState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.modal.DefaultRootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
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
            ComposeApplication(rootModule.componentsModule.themeSwitcherComponent.value) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootBottomSheetContent(
    rootBottomSheetComponent: RootBottomSheetComponent,
    content: @Composable () -> Unit
) {
    val slot by rootBottomSheetComponent.childSlot.subscribeAsState()
    val bottomSheetState = rememberDeclarativeModalBottomSheetState(
        slot.child,
        onDismiss = rootBottomSheetComponent::dismissSlotChild
    ) { child ->
        when (val child = child.instance) {
            is DefaultRootBottomSheetComponent.Configuration.SettingsChild -> {
                InfoScreen(child.linkBrowser)
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState.sheetState,
        sheetContent = bottomSheetState.sheetContent.value,
        sheetShape = RoundedCornerShape(AppTheme.dimens.S),
        sheetBackgroundColor = MaterialTheme.colors.primary,
        content = content
    )
}
