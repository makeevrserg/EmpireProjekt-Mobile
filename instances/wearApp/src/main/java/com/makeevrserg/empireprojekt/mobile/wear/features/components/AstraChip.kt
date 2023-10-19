package com.makeevrserg.empireprojekt.mobile.wear.features.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun AstraChip(
    label: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable BoxScope.() -> Unit)? = null
) {
    Chip(
        modifier = modifier,
        label = label,
        onClick = onClick,
        icon = icon,
        colors = ChipDefaults.chipColors(
            backgroundColor = AppTheme.materialColor.primary,
            contentColor = AppTheme.materialColor.onPrimary,
            secondaryContentColor = AppTheme.materialColor.secondary,
            iconColor = AppTheme.materialColor.onPrimary,
            disabledBackgroundColor = AppTheme.materialColor.primary.copy(0.5f),
            disabledContentColor = AppTheme.materialColor.onPrimary.copy(0.5f),
            disabledSecondaryContentColor = AppTheme.materialColor.secondary.copy(0.5f),
            disabledIconColor = AppTheme.materialColor.onPrimary.copy(0.5f)
        ),
    )
}

@Composable
fun IconTextChip(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    textColor: Color = AppTheme.materialColor.onPrimary,
    iconColor: Color = Color.Unspecified,
    onClick: () -> Unit = { }
) {
    AstraChip(
        modifier = modifier,
        label = {
            Crossfade(targetState = text, label = "LABEL") { text ->
                Text(
                    text = text,
                    style = AppTheme.typography.caption,
                    color = textColor
                )
            }
        },
        onClick = onClick,
        icon = {
            Crossfade(targetState = imageVector, label = "LABEL") { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .size(ChipDefaults.LargeIconSize)
                        .wrapContentSize(align = Alignment.Center),
                    tint = iconColor
                )
            }
        }
    )
}
