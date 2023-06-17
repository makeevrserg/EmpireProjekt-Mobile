package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.di.RootModule
import com.makeevrserg.empireprojekt.mobile.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.ui.splash.SplashScreenComponent
import com.makeevrserg.empireprojekt.mobile.navigation.DefaultRootComponent
import com.makeevrserg.mobilex.di.getValue

@Composable
fun ApplicationContent(
    component: DefaultRootComponent,
    modifier: Modifier = Modifier
) {
    val rootModule by RootModule
    val servicesModule by ServicesModule

    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(slide())
    ) { configuration ->

        when (val screen = configuration.instance) {
            is DefaultRootComponent.Configuration.Splash -> SplashScreenComponent(
                rootComponent = component,
                splashComponent = screen.splashComponent
            )
        }
    }
}
