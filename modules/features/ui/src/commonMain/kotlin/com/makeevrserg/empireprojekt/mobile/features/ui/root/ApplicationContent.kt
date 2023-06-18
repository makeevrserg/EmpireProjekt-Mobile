package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.splash.SplashScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.StatusScreen
import com.makeevrserg.mobilex.di.getValue

@Composable
fun ApplicationContent(
    component: DefaultRootComponent,
    modifier: Modifier = Modifier
) {
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

            is DefaultRootComponent.Configuration.Status -> StatusScreen(
                listOf(
                    screen.ainteractiveStatusComponent,
                    screen.esmpStatusComponent,
                    screen.alearnerProdStatusComponent,
                    screen.alearnerDevStatusComponent
                )
            )
        }
    }
}
