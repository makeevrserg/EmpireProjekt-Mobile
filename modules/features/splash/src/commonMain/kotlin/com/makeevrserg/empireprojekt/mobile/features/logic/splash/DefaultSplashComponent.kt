package com.makeevrserg.empireprojekt.mobile.features.logic.splash

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import dev.icerock.moko.mvvm.flow.cFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DefaultSplashComponent(
    context: ComponentContext,
    module: SplashComponentModule
) : SplashComponent,
    SplashComponentModule by module,
    ComponentContext by context {
    private val _screenChannel = Channel<SplashComponent.Label>()
    override val screenChannelFlow = _screenChannel.consumeAsFlow().cFlow()

    init {
        mainScope.launch(dispatchers.IO) {
            val isInitialLaunch = repository.isInitialLaunch()
            val label = SplashComponent.Label.InitialLaunch(isInitialLaunch)
            _screenChannel.send(label)
        }
    }
}
