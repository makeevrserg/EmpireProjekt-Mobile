package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponent
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.SplashComponentImpl
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.splash.SplashComponentModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.status.StatusModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature

class DefaultRootComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
    servicesModule: ServicesModule
) : RootComponent, ComponentContext by componentContext {
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
                    val esmpStatusComponent = UrlStatusComponent(
                        context = context,
                        url = "https://empireprojekt.ru",
                        title = "empireprojekt.ru",
                        module = StatusModuleImpl(),
                        coroutineFeature = context.instanceKeeper.getOrCreate {
                            CoroutineFeature.Default()
                        }
                    )
                    val ainteractiveStatusComponent = UrlStatusComponent(
                        context = context,
                        url = "https://astrainteractive.ru",
                        title = "astrainteractive.ru",
                        module = StatusModuleImpl(),
                        coroutineFeature = context.instanceKeeper.getOrCreate {
                            CoroutineFeature.Default()
                        }
                    )

                    val alearnerDevStatusComponent = UrlStatusComponent(
                        context = context,
                        url = "http://astralearner.empireprojekt.ru:8083/dictionaries/4/words",
                        title = "Dev: AstraLearner",
                        module = StatusModuleImpl(),
                        coroutineFeature = context.instanceKeeper.getOrCreate {
                            CoroutineFeature.Default()
                        }
                    )

                    val alearnerProdStatusComponent = UrlStatusComponent(
                        context = context,
                        url = "http://astralearner.empireprojekt.ru:8081/dictionaries/4/words",
                        title = "Prod: AstraLearner",
                        module = StatusModuleImpl(),
                        coroutineFeature = context.instanceKeeper.getOrCreate {
                            CoroutineFeature.Default()
                        }
                    )
                    Configuration.Status(
                        esmpStatusComponent = esmpStatusComponent,
                        ainteractiveStatusComponent = ainteractiveStatusComponent,
                        alearnerDevStatusComponent = alearnerDevStatusComponent,
                        alearnerProdStatusComponent = alearnerProdStatusComponent
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
            val esmpStatusComponent: StatusComponent,
            val ainteractiveStatusComponent: StatusComponent,
            val alearnerProdStatusComponent: StatusComponent,
            val alearnerDevStatusComponent: StatusComponent
        ) : Configuration
    }
}
