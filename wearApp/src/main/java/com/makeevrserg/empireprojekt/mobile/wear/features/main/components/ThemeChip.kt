package com.makeevrserg.empireprojekt.mobile.wear.features.main.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
fun ThemeChip(themeSwitcherComponent: ThemeSwitcherComponent) {
    val theme by themeSwitcherComponent.theme.collectAsState()
    val icon = when (theme) {
        ThemeSwitcherComponent.Theme.DARK -> Icons.Filled.Bedtime
        ThemeSwitcherComponent.Theme.LIGHT -> Icons.Filled.WbSunny
    }
    val color by animateColorAsState(
        targetValue = when (theme) {
            ThemeSwitcherComponent.Theme.DARK -> AppTheme.materialColor.onPrimary
            ThemeSwitcherComponent.Theme.LIGHT -> AppTheme.materialColor.onPrimary
        },
        label = "LABEL"
    )
    AstraChip(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = "Switch theme",
                style = AppTheme.typography.caption,
                color = AppTheme.materialColor.onPrimary
            )
        },
        onClick = themeSwitcherComponent::next,
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
