package com.makeevrserg.empireprojekt.mobile.features.splash.presentation

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.splash.di.SplashComponentDependencies
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

internal class DefaultSplashComponent(
    context: ComponentContext,
    dependencies: SplashComponentDependencies
) : SplashComponent,
    SplashComponentDependencies by dependencies,
    ComponentContext by context {
    private val _screenChannel = Channel<SplashComponent.Label>()
    override val screenChannelFlow = _screenChannel.consumeAsFlow()

    init {
        mainScope.launch(dispatchers.IO) {
            val isInitialLaunch = repository.isInitialLaunch()
            val label = SplashComponent.Label.InitialLaunch(isInitialLaunch)
            _screenChannel.send(label)
        }
    }
}
