package com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.DefaultSplashComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.DefaultRatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.di.RatingUserModule
import com.makeevrserg.empireprojekt.mobile.features.rating.users.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
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

            RootScreenComponent.Child.Status -> {
                DefaultRootScreenComponent.Configuration.Status(
                    themeSwitcherComponent = rootModule.componentsModule.themeSwitcherComponent.value,
                    rootStatusComponent = rootModule.componentsModule.rootStatusComponent.value
                )
            }

            RootScreenComponent.Child.RatingUsers -> {
                DefaultRootScreenComponent.Configuration.RatingUsers(
                    ratingUsersComponent = DefaultRatingUsersComponent(
                        componentContext = context,
                        moduleFactory = {
                            RatingUsersModule.Default(
                                empireApiModule = rootModule.empireApiModule,
                                dispatchers = rootModule.servicesModule.dispatchers.value
                            )
                        },
                        onShowUserRatingsClicked = { id ->
                            val configuration = RootScreenComponent.Child.RatingUser(id)
                            instance.push(configuration)
                        }
                    )
                )
            }

            is RootScreenComponent.Child.RatingUser -> {
                val module = RatingUserModule.Default(
                    empireApiModule = rootModule.empireApiModule,
                    dispatchers = rootModule.servicesModule.dispatchers.value
                )
                DefaultRootScreenComponent.Configuration.RatingUser(
                    ratingUserComponent = DefaultRatingUserComponent(
                        componentContext = context,
                        userId = config.userId,
                        repository = module.ratingUserRepository
                    )
                )
            }
        }
    }
}
