package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun RowSettingItem(
    modifier: Modifier = Modifier,
    text: String,
    spacing: Dp = AppTheme.dimens.S,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    prefix: (@Composable RowScope.() -> Unit)? = null,
    postfix: (@Composable RowScope.() -> Unit)? = null,
) {
    RowSettingItem(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        spacing = spacing,
        prefix = prefix,
        postfix = postfix,
        middle = {
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
        }
    )
}

@Composable
fun RowSettingItem(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    spacing: Dp = AppTheme.dimens.S,
    middle: (@Composable RowScope.() -> Unit)? = null,
    prefix: (@Composable RowScope.() -> Unit)? = null,
    postfix: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        prefix?.let {
            prefix(this)
            Spacer(Modifier.width(spacing))
        }
        middle?.let {
            middle(this)
            Spacer(Modifier.width(spacing))
        }
        postfix?.invoke(this)
    }
}
