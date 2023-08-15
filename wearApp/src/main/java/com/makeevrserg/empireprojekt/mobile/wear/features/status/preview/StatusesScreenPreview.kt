package com.makeevrserg.empireprojekt.mobile.wear.features.status.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.status.StubStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.root.ComposeApplication
import com.makeevrserg.empireprojekt.mobile.wear.features.status.StatusesScreen

@Preview
@Composable
private fun StatusesScreenPreview() {
    val items = List(30) {
        StubStatusComponent()
    }
    ComposeApplication {
        StatusesScreen(items)
    }
}
