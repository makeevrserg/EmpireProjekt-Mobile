package com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.DefaultSplashComponent
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import ru.astrainteractive.klibs.kdi.Factory

class RootScreenComponentChildFactory(
    private val config: RootScreenComponent.Child,
    private val context: ComponentContext,
    private val rootModule: RootModule
) : Factory<DefaultRootScreenComponent.Configuration> {
    override fun create(): DefaultRootScreenComponent.Configuration {
        return when (config) {
            RootScreenComponent.Child.Splash -> DefaultRootScreenComponent.Configuration.Splash(
                splashComponent = DefaultSplashComponent(
                    context = context,
                    module = rootModule.splashModule
                )
            )

            RootScreenComponent.Child.Status -> {
                DefaultRootScreenComponent.Configuration.Status(
                    themeSwitcherComponent = rootModule.componentsModule.themeSwitcherComponent.value,
                    rootStatusComponent = rootModule.componentsModule.rootStatusComponent.value
                )
            }
        }
    }
}
