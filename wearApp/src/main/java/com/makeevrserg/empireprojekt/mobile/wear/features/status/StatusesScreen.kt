package com.makeevrserg.empireprojekt.mobile.wear.features.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.features.status.components.StatusWidget

@Composable
fun StatusesScreen(wearStatusComponent: WearStatusComponent) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        modifier = Modifier.background(AppTheme.materialColor.primaryVariant),
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState
            )
        }
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
        ) {
            if (wearStatusComponent.statuses.isEmpty()) {
                item {
                    Text(
                        text = "No items present",
                        style = AppTheme.typography.caption,
                        color = AppTheme.materialColor.onPrimary
                    )
                }
            }
            items(wearStatusComponent.statuses) {
                StatusWidget(it)
            }
        }
    }
}
