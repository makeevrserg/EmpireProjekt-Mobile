package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.features.theme.ApplicationTheme
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.status.ui.StatusesScreen

@Preview
@Composable
private fun StatusesScreenPreview() {
    ApplicationTheme {
        StatusesScreen(WearStatusComponent.Stub())
    }
}
