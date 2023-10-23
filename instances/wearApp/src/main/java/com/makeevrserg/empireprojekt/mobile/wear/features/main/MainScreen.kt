package com.makeevrserg.empireprojekt.mobile.wear.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.main.components.NavChip
import com.makeevrserg.empireprojekt.mobile.wear.features.main.components.ThemeChip
import com.makeevrserg.empireprojekt.mobile.wear.features.root.NavHostRootComponent
import ru.astrainteractive.klibs.kdi.getValue

@Composable
fun MainScreen(
    wearRootModule: WearRootModule,
    rootComponent: NavHostRootComponent
) {
    val themeSwitcher by wearRootModule.themeSwitcherComponent
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.dimens.M),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            ThemeChip(themeSwitcherComponent = themeSwitcher)
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = "Statuses",
                onClick = {
                    rootComponent.openStatuses()
                }
            )
        }
    }
}
