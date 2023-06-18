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
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent

@Composable
internal fun IconWidget(it: StatusComponent.Model.LoadingStatus) {
    val icon = when (it) {
        StatusComponent.Model.LoadingStatus.LOADING -> Icons.Filled.Token
        StatusComponent.Model.LoadingStatus.SUCCESS -> Icons.Filled.Bolt
        StatusComponent.Model.LoadingStatus.ERROR -> Icons.Filled.Error
    }
    val tint = when (it) {
        StatusComponent.Model.LoadingStatus.LOADING -> AppTheme.alColors.astraYellow
        StatusComponent.Model.LoadingStatus.SUCCESS -> AppTheme.alColors.colorPositive
        StatusComponent.Model.LoadingStatus.ERROR -> AppTheme.alColors.colorNegative
    }
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
    )
}
