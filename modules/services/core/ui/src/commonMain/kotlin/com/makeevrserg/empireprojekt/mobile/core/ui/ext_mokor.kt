@file:Suppress("Filename")

package com.makeevrserg.empireprojekt.mobile.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
expect fun StringResource.asComposableString(): String

@Composable
expect fun StringDesc.asComposableString(): String

@Composable
expect fun ImageResource.asPainter(): Painter
