package com.makeevrserg.empireprojekt.mobile.wear.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation.DefaultPingComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    wearRootModule: WearRootModule,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(Configuration.Main, Configuration.Ping) },
        handleBackButton = true,
        childFactory = { config, childContext ->
            when (config) {
                Configuration.Main -> RootComponent.Child.Main(
                    themeSwitcherComponent = wearRootModule.themeSwitcherModule.themeSwitcherComponent
                )

                Configuration.Ping -> RootComponent.Child.Ping(
                    pingComponent = DefaultPingComponent(
                        componentContext = childContext,
                        wearMessageProducer = wearRootModule.wearMessengerModule.wearMessageProducer,
                        wearMessageConsumer = wearRootModule.wearMessengerModule.wearMessageConsumer,
                    )
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

    sealed interface Configuration : Parcelable {
        @Parcelize
        data object Main : Configuration

        @Parcelize
        data object Statuses : Configuration

        @Parcelize
        data object Ping : Configuration
    }
}
