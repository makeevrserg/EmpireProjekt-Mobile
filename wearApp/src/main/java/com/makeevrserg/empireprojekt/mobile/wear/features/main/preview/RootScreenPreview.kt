package com.makeevrserg.empireprojekt.mobile.wear.features.main.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.main.MainScreen

@Preview
@Composable
private fun RootScreenPreview() {
    ComposeApplication {
        MainScreen(wearRootModule = WearRootModule)
    }
}
