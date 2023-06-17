package com.makeevrserg.empireprojekt.mobile.features.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponent
import com.makeevrserg.empireprojekt.mobile.navigation.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.resources.MR
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenComponent(
    splashComponent: SplashComponent,
    rootComponent: DefaultRootComponent
) {
    LaunchedEffect(key1 = Unit) {
        splashComponent.screenChannelFlow.collectLatest {
            when (it) {
                is SplashComponent.Label.InitialLaunch -> Unit
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(AppTheme.materialColor.primaryVariant),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = MR.images.ic_splash.asPainter(),
            modifier = Modifier.size(256.dp),
            contentDescription = null
        )
    }
}
