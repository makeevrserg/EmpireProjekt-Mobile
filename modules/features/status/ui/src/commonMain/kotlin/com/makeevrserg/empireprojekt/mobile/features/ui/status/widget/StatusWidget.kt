package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent

private const val FADE_DURATION = 1200

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun StatusWidget(statusComponent: UrlStatusComponent) {
    val model by statusComponent.model.collectAsState()

    Row(
        modifier = Modifier
            .padding(vertical = AppTheme.dimens.M)
            .height(54.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .background(MaterialTheme.colors.primary)
            .clickable(onClick = statusComponent::checkStatus),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = (model.status to model.isLoading),
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            },
            label = "null"
        ) { (status, isLoading) ->
            SideColorStatusWidget(status, isLoading)
        }

        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = model.status,
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            },
            label = "null"
        ) {
            IconWidget(it)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.title.asComposableString(),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
