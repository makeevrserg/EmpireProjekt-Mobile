package com.makeevrserg.empireprojekt.mobile.features.ui.info.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.ImageResource

class LinkModel(
    val res: ImageResource,
    val title: String,
    val url: String,
    val tint: @Composable () -> Color = { Color.Unspecified }
)
