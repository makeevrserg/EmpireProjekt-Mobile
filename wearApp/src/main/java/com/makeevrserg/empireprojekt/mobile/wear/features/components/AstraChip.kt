package com.makeevrserg.empireprojekt.mobile.wear.features.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
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
