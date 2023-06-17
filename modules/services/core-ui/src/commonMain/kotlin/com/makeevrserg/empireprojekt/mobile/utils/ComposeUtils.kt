package com.makeevrserg.empireprojekt.mobile.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier

object ComposeUtils {
    fun Modifier.simpleAnimation() = this.animateContentSize(
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    )
}
