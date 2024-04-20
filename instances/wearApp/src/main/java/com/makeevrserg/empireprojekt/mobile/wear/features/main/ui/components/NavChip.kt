package com.makeevrserg.empireprojekt.mobile.wear.features.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.features.components.AstraChip

@Composable
fun NavChip(text: String, onClick: () -> Unit) {
    AstraChip(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary
            )
        },
        onClick = onClick,
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
