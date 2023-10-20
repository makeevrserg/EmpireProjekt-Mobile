package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.url.UrlStatusComponent

@Composable
internal fun SideColorStatusWidget(
    status: UrlStatusComponent.LoadingStatus,
    isLoading: Boolean
) {
    when {
        status == UrlStatusComponent.LoadingStatus.LOADING || isLoading -> {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS),
                color = AppTheme.customColors.astraOrange,
                backgroundColor = AppTheme.customColors.astraYellow
            )
        }

        status == UrlStatusComponent.LoadingStatus.SUCCESS -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.customColors.colorPositive)
            )
        }

        status == UrlStatusComponent.LoadingStatus.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.customColors.colorNegative)
            )
        }
    }
}
