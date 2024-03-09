package com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.DefaultSplashComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.DefaultRatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import ru.astrainteractive.klibs.kdi.Factory

class RootScreenComponentChildFactory(
    private val config: RootScreenComponent.Child,
    private val context: ComponentContext,
    private val rootModule: RootModule,
    private val instance: RootScreenComponent
) : Factory<DefaultRootScreenComponent.Configuration> {
    override fun create(): DefaultRootScreenComponent.Configuration {
        return when (config) {
            RootScreenComponent.Child.Splash -> DefaultRootScreenComponent.Configuration.Splash(
                splashComponent = DefaultSplashComponent(
                    context = context,
                    module = rootModule.splashModule
                )
            )

            is RootScreenComponent.Child.RatingUser -> {
                val module = RatingUserModule.Default(
                    empireApiModule = rootModule.empireApiModule,
                    dispatchers = rootModule.servicesModule.dispatchers.value
                )
                DefaultRootScreenComponent.Configuration.RatingUser(
                    ratingUserComponent = DefaultRatingUserComponent(
                        componentContext = context,
                        userId = config.userId,
                        repository = module.ratingUserRepository,
                        userName = config.userName
                    )
                )
            }

            RootScreenComponent.Child.Pager -> {
                DefaultRootScreenComponent.Configuration.Pager(
                    pagerComponent = rootModule.pagerModule.createPagerComponent(
                        componentContext = context,
                        onRootNavigation = { configuration ->
                            instance.push(configuration)
                        }
                    )
                )
            }
        }
    }
}
