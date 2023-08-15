package com.makeevrserg.empireprojekt.mobile.wear.features.status.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
internal fun StatusWidget(component: StatusComponent) {
    val model by component.model.collectAsState()
    val icon = when (model.status) {
        StatusComponent.Model.LoadingStatus.LOADING -> Icons.Filled.WifiTetheringError
        StatusComponent.Model.LoadingStatus.SUCCESS -> Icons.Filled.WifiTethering
        StatusComponent.Model.LoadingStatus.ERROR -> Icons.Filled.WifiTetheringOff
    }
    val color by animateColorAsState(
        targetValue = when (model.status) {
            StatusComponent.Model.LoadingStatus.LOADING -> AppTheme.alColors.astraOrange
            StatusComponent.Model.LoadingStatus.SUCCESS -> AppTheme.alColors.colorPositive
            StatusComponent.Model.LoadingStatus.ERROR -> AppTheme.alColors.colorNegative
        },
        label = "LABEL"
    )
    AstraChip(
        label = {
            Text(
                text = "EmpireProjekt.ru",
                style = AppTheme.typography.caption,
                color = AppTheme.materialColor.onPrimary
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
