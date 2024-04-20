package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.MainScreen

@Preview(device = "id:wearos_large_round")
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
