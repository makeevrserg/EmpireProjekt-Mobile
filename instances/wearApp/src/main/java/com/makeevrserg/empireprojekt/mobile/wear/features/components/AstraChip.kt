package com.makeevrserg.empireprojekt.mobile.wear.features.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

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
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            secondaryContentColor = MaterialTheme.colors.secondary,
            iconColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(0.5f),
            disabledContentColor = MaterialTheme.colors.onPrimary.copy(0.5f),
            disabledSecondaryContentColor = MaterialTheme.colors.secondary.copy(0.5f),
            disabledIconColor = MaterialTheme.colors.onPrimary.copy(0.5f)
        ),
    )
}

@Composable
fun IconTextChip(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    iconColor: Color = Color.Unspecified,
    onClick: () -> Unit = { }
) {
    AstraChip(
        modifier = modifier,
        label = {
            Crossfade(targetState = text, label = "LABEL") { text ->
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
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
