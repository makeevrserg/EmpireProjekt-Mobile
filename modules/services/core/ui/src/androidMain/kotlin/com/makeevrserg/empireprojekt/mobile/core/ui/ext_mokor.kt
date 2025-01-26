@file:Suppress("Filename")

package com.makeevrserg.empireprojekt.mobile.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
actual fun StringResource.asComposableString(): String {
    return StringDesc.Resource(this).localized()
}

@Composable
actual fun StringDesc.asComposableString(): String {
    return this.localized()
}

@Composable
actual fun ImageResource.asPainter(): Painter {
    return painterResource(id = this.drawableResId)
}
