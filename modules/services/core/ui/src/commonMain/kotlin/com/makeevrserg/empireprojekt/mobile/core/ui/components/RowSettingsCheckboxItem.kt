package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun RowSettingsCheckboxItem(
    icon: ImageVector? = null,
    text: String,
    spacing: Dp = AppTheme.dimens.S,
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckChange: (Boolean) -> Unit
) {
    RowSettingItem(
        modifier = modifier,
        text = text,
        prefix = {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(AppTheme.dimens.M)
                )
            }
        },
        spacing = spacing,
        postfix = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckChange,
                enabled = enabled,
                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colors.secondaryVariant)
            )
        }
    )
}
