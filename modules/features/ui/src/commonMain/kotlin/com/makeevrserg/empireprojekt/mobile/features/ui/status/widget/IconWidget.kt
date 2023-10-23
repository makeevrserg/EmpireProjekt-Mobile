package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Token
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.url.UrlStatusComponent

@Composable
internal fun IconWidget(it: UrlStatusComponent.LoadingStatus) {
    val icon = when (it) {
        UrlStatusComponent.LoadingStatus.LOADING -> Icons.Filled.Token
        UrlStatusComponent.LoadingStatus.SUCCESS -> Icons.Filled.Bolt
        UrlStatusComponent.LoadingStatus.ERROR -> Icons.Filled.Error
    }
    val tint = when (it) {
        UrlStatusComponent.LoadingStatus.LOADING -> AppTheme.customColors.astraYellow
        UrlStatusComponent.LoadingStatus.SUCCESS -> AppTheme.customColors.colorPositive
        UrlStatusComponent.LoadingStatus.ERROR -> AppTheme.customColors.colorNegative
    }
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
    )
}
