package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import dev.icerock.moko.resources.ImageResource

@Composable
fun RowSettingChevronItem(
    modifier: Modifier = Modifier,
    text: String,
    spacing: Dp = AppTheme.dimens.S,
    prefix: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    RowSettingItem(
        modifier = modifier,
        text = text,
        spacing = spacing,
        prefix = prefix,
        postfix = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(AppTheme.dimens.M)
                )
            }
        },
    )
}

@Composable
fun RowSettingChevronItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    spacing: Dp = AppTheme.dimens.S,
    text: String,
    onClick: (() -> Unit) = {}
) {
    RowSettingChevronItem(
        modifier = modifier,
        text = text,
        onClick = onClick,
        spacing = spacing,
        prefix = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(AppTheme.dimens.M)
            )
        }
    )
}

@Composable
fun RowSettingTextInfo(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    spacing: Dp = AppTheme.dimens.S,
    text: String,
    endText: String,
) {
    RowSettingItem(
        modifier = modifier,
        text = text,
        spacing = spacing,
        prefix = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(AppTheme.dimens.M)
            )
        },
        postfix = {
            Text(
                text = endText,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(end = AppTheme.dimens.M)
            )
        },
    )
}

@Composable
fun RowSettingChevronItem(
    modifier: Modifier = Modifier,
    icon: ImageResource,
    tint: Color = Color.Unspecified,
    text: String,
    onClick: (() -> Unit) = {}
) {
    RowSettingChevronItem(
        modifier = modifier,
        text = text,
        onClick = onClick,
        prefix = {
            Icon(
                painter = icon.asPainter(),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(AppTheme.dimens.M)
            )
        }
    )
}
