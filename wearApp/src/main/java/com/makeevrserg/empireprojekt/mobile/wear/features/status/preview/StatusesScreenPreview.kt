package com.makeevrserg.empireprojekt.mobile.wear.features.status.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.features.status.StatusesScreen
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent

@Preview
@Composable
private fun StatusesScreenPreview() {
    ComposeApplication {
        StatusesScreen(WearStatusComponent.Stub())
    }
}
