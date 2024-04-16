package com.makeevrserg.empireprojekt.mobile.wear.features.main.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen

@Preview
@Composable
private fun RootScreenPreview() {
    ApplicationTheme {
        MainScreen(
            themeSwitcherComponent = PreviewThemeSwitcherComponent(),
            onOpenStatusesClicked = {},
            onOpenPingClicked = {}
        )
    }
}
