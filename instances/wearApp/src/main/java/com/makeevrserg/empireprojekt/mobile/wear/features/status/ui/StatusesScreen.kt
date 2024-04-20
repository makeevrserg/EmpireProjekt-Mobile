package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material.icons.filled.WifiTetheringError
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.features.components.IconTextChip
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent

@Composable
fun StatusesScreen(wearStatusComponent: WearStatusComponent) {
    val mergedState by wearStatusComponent.mergedState.collectAsState()
    val listState = rememberScalingLazyListState()
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
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
                Text(
                    text = mergedState.updatedAt,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.successCount.toString(),
                    imageVector = Icons.Filled.WifiTethering,
                    iconColor = AppTheme.customColors.colorPositive
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.loadingCount.toString(),
                    imageVector = Icons.Filled.WifiTetheringError,
                    iconColor = AppTheme.customColors.astraOrange
                )
            }
            item {
                IconTextChip(
                    modifier = Modifier.fillMaxWidth(),
                    text = mergedState.failureCount.toString(),
                    imageVector = Icons.Filled.WifiTetheringOff,
                    iconColor = AppTheme.customColors.colorNegative
                )
            }
        }
    }
}
