package com.makeevrserg.empireprojekt.mobile.wear.features.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
fun NavChip(text: String) {
    AstraChip(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = text,
                style = AppTheme.typography.caption,
                color = AppTheme.materialColor.onPrimary
            )
        },
        onClick = {},
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(ChipDefaults.LargeIconSize)
                    .wrapContentSize(align = Alignment.Center),
            )
        }
    )
}
