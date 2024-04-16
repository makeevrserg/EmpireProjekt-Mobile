package com.makeevrserg.empireprojekt.mobile.wear.features.main.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
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
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
fun ThemeChip(themeSwitcherComponent: ThemeSwitcherComponent) {
    val theme by themeSwitcherComponent.theme.collectAsState()
    val icon = when (theme) {
        Theme.DARK -> Icons.Filled.Bedtime
        Theme.LIGHT -> Icons.Filled.WbSunny
    }
    val color by animateColorAsState(
        targetValue = when (theme) {
            Theme.DARK -> MaterialTheme.colors.onPrimary
            Theme.LIGHT -> MaterialTheme.colors.onPrimary
        },
        label = "LABEL"
    )
    AstraChip(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = "Switch theme",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary
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
