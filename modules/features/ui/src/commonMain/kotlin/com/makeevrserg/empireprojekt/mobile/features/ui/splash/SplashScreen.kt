package com.makeevrserg.empireprojekt.mobile.features.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.makeevrserg.empireprojekt.mobile.core.ui.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.resources.MR
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenComponent(
    splashComponent: SplashComponent,
    rootComponent: RootComponent
) {
    LaunchedEffect(key1 = Unit) {
        splashComponent.screenChannelFlow.collectLatest {
            when (it) {
                is SplashComponent.Label.InitialLaunch -> {
                    rootComponent.rootScreenComponent.replaceCurrent(RootScreenComponent.Child.Status)
                }
            }
        }
    }
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = MR.images.ic_splash.asPainter(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .navBarsPadding(),
            contentAlignment = Alignment.TopCenter
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.customColors.astraOrange,
                backgroundColor = AppTheme.customColors.astraYellow
            )
        }
    }
}
