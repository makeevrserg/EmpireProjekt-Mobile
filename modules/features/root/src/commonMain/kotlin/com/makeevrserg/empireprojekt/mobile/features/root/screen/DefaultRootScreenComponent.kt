package com.makeevrserg.empireprojekt.mobile.features.root.screen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.user.RatingUserComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.di.factory.RootScreenComponentChildFactory
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent

class DefaultRootScreenComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
) : RootScreenComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootScreenComponent.Child>()

    override val childStack: Value<ChildStack<*, Configuration>> = childStack(
        source = navigation,
        initialConfiguration = RootScreenComponent.Child.Splash,
        handleBackButton = true,
        childFactory = { config, context ->
            RootScreenComponentChildFactory(
                config = config,
                context = context,
                rootModule = rootModule
            ).create()
        }
    )

    override fun push(screen: RootScreenComponent.Child) {
        navigation.push(screen)
    }

    override fun replaceCurrent(screen: RootScreenComponent.Child) {
        navigation.replaceCurrent(screen)
    }

    override fun replaceAll(screen: RootScreenComponent.Child) {
        navigation.replaceAll(screen)
    }

    override fun pop() {
        navigation.pop()
    }

    sealed interface Configuration {

        class Splash(
            val splashComponent: SplashComponent
        ) : Configuration

        class Status(
            val rootStatusComponent: RootStatusComponent,
            val themeSwitcherComponent: ThemeSwitcherComponent
        ) : Configuration

        class RatingUsers(
            val ratingUsersComponent: RatingUsersComponent
        ) : Configuration

        class RatingUser(
            val ratingUserComponent: RatingUserComponent
        ) : Configuration
    }
}
