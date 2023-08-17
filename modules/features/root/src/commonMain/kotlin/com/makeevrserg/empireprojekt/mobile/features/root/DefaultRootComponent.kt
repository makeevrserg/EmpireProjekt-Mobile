package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponentImpl
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.splash.SplashComponentModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher

class DefaultRootComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
    servicesModule: ServicesModule
) : RootComponent, ComponentContext by componentContext {
    override val rootBottomSheetComponent: RootBottomSheetComponent =
        DefaultRootBottomSheetComponent(
            componentContext = childContext("RootBottomSheetComponent"),
            servicesModule = servicesModule,
        )
    private val navigation = StackNavigation<RootComponent.Child>()

    override val childStack: Value<ChildStack<*, Configuration>> = childStack(
        source = navigation,
        initialConfiguration = RootComponent.Child.Splash,
        handleBackButton = true,
        childFactory = { config, context ->
            when (config) {
                RootComponent.Child.Splash -> Configuration.Splash(
                    splashComponent = SplashComponentImpl(
                        context = context,
                        module = SplashComponentModuleImpl(
                            rootModule = rootModule,
                            servicesModule = servicesModule
                        )
                    )
                )

                RootComponent.Child.Status -> {
                    Configuration.Status(
                        themeSwitcher = rootModule.themeSwitcher.value,
                        rootStatusComponent = rootModule.rootStatusComponent.value
                    )
                }
            }
        }
    )

    override fun push(screen: RootComponent.Child) {
        navigation.push(screen)
    }

    override fun replaceCurrent(screen: RootComponent.Child) {
        navigation.replaceCurrent(screen)
    }

    override fun replaceAll(screen: RootComponent.Child) {
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
            val themeSwitcher: ThemeSwitcher
        ) : Configuration
    }
}
