package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Token
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent

private const val FADE_DURATION = 1200

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StatusWidget(statusComponent: StatusComponent) {
    val model by statusComponent.model.collectAsState()

    Row(
        modifier = Modifier.padding(vertical = AppTheme.dimens.M)
            .height(54.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .background(AppTheme.materialColor.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = (model.status to model.isLoading),
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            }
        ) { (status, isLoading) ->
            when {
                status == StatusComponent.Model.LoadingStatus.LOADING || isLoading -> {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxHeight().width(AppTheme.dimens.XXS),
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

        AnimatedContent(
            targetState = model.status,
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            }
        ) {
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

        Column {
            Text(
                text = model.title.asComposableString(),
                style = AppTheme.typography.h6,
                color = AppTheme.materialColor.onPrimary
            )
        }
    }
}
