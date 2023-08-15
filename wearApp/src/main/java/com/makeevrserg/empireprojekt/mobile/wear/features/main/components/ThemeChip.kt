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
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
fun ThemeChip(themeSwitcher: ThemeSwitcher) {
    val theme by themeSwitcher.theme.collectAsState()
    val icon = when (theme) {
        ThemeSwitcher.Theme.DARK -> Icons.Filled.Bedtime
        ThemeSwitcher.Theme.LIGHT -> Icons.Filled.WbSunny
    }
    val color by animateColorAsState(
        targetValue = when (theme) {
            ThemeSwitcher.Theme.DARK -> AppTheme.materialColor.onPrimary
            ThemeSwitcher.Theme.LIGHT -> AppTheme.materialColor.onPrimary
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
        onClick = themeSwitcher::next,
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
