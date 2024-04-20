package com.makeevrserg.empireprojekt.mobile.wear.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

class DefaultRootComponent(
    componentContext: ComponentContext,
    wearRootModule: WearRootModule,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.Main,
        serializer = wearRootModule.coreModule.jsonConfiguration.serializersModule.serializer(),
        handleBackButton = true,
        childFactory = { config, childContext ->
            when (config) {
                Configuration.Main -> RootComponent.Child.Main(
                    themeSwitcherComponent = wearRootModule.themeSwitcherModule.themeSwitcherComponent
                )

                Configuration.Ping -> RootComponent.Child.Ping(
                    pingComponent = wearRootModule.pingModule.createPingComponent(childContext)
                )

                Configuration.Statuses -> RootComponent.Child.Statuses(
                    wearStatusComponent = wearRootModule.wearStatusComponent.value
                )
            }
        }
    )

    override fun openStatuses() {
        navigation.push(Configuration.Statuses)
    }

    override fun openPing() {
        navigation.push(Configuration.Ping)
    }

    override fun pop() {
        navigation.pop()
    }

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Main : Configuration

        @Serializable
        data object Statuses : Configuration

        @Serializable
        data object Ping : Configuration
    }
}
