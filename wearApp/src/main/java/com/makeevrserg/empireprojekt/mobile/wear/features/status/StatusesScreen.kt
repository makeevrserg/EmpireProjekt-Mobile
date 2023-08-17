package com.makeevrserg.empireprojekt.mobile.wear.features.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material.icons.filled.WifiTetheringError
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip
import com.makeevrserg.empireprojekt.mobile.wear.features.status.components.StatusWidget

@Composable
fun StatusesScreen(wearStatusComponent: WearStatusComponent) {
    val mergedState by wearStatusComponent.mergedState.collectAsState()
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
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.successCount.toString(),
                    imageVector = Icons.Filled.WifiTethering,
                    iconColor = AppTheme.alColors.colorPositive
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.loadingCount.toString(),
                    imageVector = Icons.Filled.WifiTetheringError,
                    iconColor = AppTheme.alColors.astraOrange
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.failureCount.toString(),
                    imageVector = Icons.Filled.WifiTetheringOff,
                    iconColor = AppTheme.alColors.colorNegative
                )
            }

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
