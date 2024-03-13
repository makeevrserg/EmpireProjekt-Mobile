package com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import ru.astrainteractive.klibs.kdi.Factory

class RootScreenComponentChildFactory(
    private val config: RootRouter.Configuration,
    private val childContext: ComponentContext,
    private val rootModule: RootModule,
    private val instance: RootScreenComponent
) : Factory<DefaultRootScreenComponent.Configuration> {
    override fun create(): DefaultRootScreenComponent.Configuration {
        return when (config) {
            RootRouter.Configuration.Splash -> {
                DefaultRootScreenComponent.Configuration.Splash(
                    splashComponent = rootModule.splashModule.createSplashComponent(
                        componentContext = childContext
                    )
                )
            }

            is RootRouter.Configuration.RatingUser -> {
                DefaultRootScreenComponent.Configuration.RatingUser(
                    ratingUserComponent = rootModule.ratingUserModule.createRatingUserComponent(
                        componentContext = childContext,
                        userId = config.userId,
                        userName = config.userName
                    )
                )
            }

            RootRouter.Configuration.Pager -> {
                DefaultRootScreenComponent.Configuration.Pager(
                    pagerComponent = rootModule.pagerModule.createPagerComponent(
                        componentContext = childContext,
                        onRootNavigation = { configuration ->
                            instance.push(configuration)
                        }
                    )
                )
            }
        }
    }
}
