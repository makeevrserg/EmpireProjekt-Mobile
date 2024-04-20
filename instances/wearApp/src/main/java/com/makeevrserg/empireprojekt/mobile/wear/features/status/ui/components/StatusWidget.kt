package com.makeevrserg.empireprojekt.mobile.wear.features.status.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material.icons.filled.WifiTetheringError
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
internal fun StatusWidget(component: UrlStatusComponent) {
    val model by component.model.collectAsState()
    val icon = when (model.status) {
        UrlStatusComponent.LoadingStatus.LOADING -> Icons.Filled.WifiTetheringError
        UrlStatusComponent.LoadingStatus.SUCCESS -> Icons.Filled.WifiTethering
        UrlStatusComponent.LoadingStatus.ERROR -> Icons.Filled.WifiTetheringOff
    }
    val color by animateColorAsState(
        targetValue = when (model.status) {
            UrlStatusComponent.LoadingStatus.LOADING -> AppTheme.customColors.astraOrange
            UrlStatusComponent.LoadingStatus.SUCCESS -> AppTheme.customColors.colorPositive
            UrlStatusComponent.LoadingStatus.ERROR -> AppTheme.customColors.colorNegative
        },
        label = "LABEL"
    )
    AstraChip(
        label = {
            Text(
                text = "EmpireProjekt.ru",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary
            )
        },
        onClick = component::checkStatus,
        icon = {
            Crossfade(targetState = icon, label = "LABEL") {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ChipDefaults.LargeIconSize)
                        .wrapContentSize(align = Alignment.Center),
                    tint = color
                )
            }
        }
    )
}
