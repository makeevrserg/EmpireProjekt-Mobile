package com.makeevrserg.empireprojekt.mobile.features.logic.splash

import dev.icerock.moko.mvvm.flow.CFlow

interface SplashComponent {
    val screenChannelFlow: CFlow<Label>

    sealed interface Label {
        class InitialLaunch(val value: Boolean) : Label
    }
}
