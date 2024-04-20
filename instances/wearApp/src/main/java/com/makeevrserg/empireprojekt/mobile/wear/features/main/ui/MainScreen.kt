package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui

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
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.NavChip
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components.ThemeChip

@Composable
fun MainScreen(
    themeSwitcherComponent: ThemeSwitcherComponent,
    onOpenStatusesClicked: () -> Unit,
    onOpenPingClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
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
            ThemeChip(themeSwitcherComponent = themeSwitcherComponent)
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = MR.strings.wear_statuses.asComposableString(),
                onClick = onOpenStatusesClicked
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.S))
            NavChip(
                text = MR.strings.wear_ping.asComposableString(),
                onClick = onOpenPingClicked
            )
        }
    }
}
