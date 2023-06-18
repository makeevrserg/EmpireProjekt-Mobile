package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent

@Composable
internal fun SideColorStatusWidget(
    status: StatusComponent.Model.LoadingStatus,
    isLoading: Boolean
) {
    when {
        status == StatusComponent.Model.LoadingStatus.LOADING || isLoading -> {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS),
                color = AppTheme.alColors.astraOrange,
                backgroundColor = AppTheme.alColors.astraYellow
            )
        }

        status == StatusComponent.Model.LoadingStatus.SUCCESS -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.alColors.colorPositive)
            )
        }

        status == StatusComponent.Model.LoadingStatus.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppTheme.dimens.XXS)
                    .background(AppTheme.alColors.colorNegative)
            )
        }
    }
}
