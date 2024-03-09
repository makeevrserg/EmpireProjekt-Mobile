package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.makeevrserg.empireprojekt.mobile.features.rating.users.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent

internal class DefaultPagerComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
    onRootNavigation: (RootScreenComponent.Child) -> Unit
) : PagerComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, PagerComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.Status,
        handleBackButton = false,
        childFactory = { config, context ->
            when (config) {
                Configuration.RatingUsers -> {
                    PagerComponent.Child.RatingUsers(
                        ratingUsersComponent = DefaultRatingUsersComponent(
                            componentContext = context,
                            moduleFactory = {
                                RatingUsersModule.Default(
                                    empireApiModule = rootModule.empireApiModule,
                                    dispatchers = rootModule.servicesModule.dispatchers.value
                                )
                            },
                            onShowUserRatingsClicked = { id, userName ->
                                val configuration = RootScreenComponent.Child.RatingUser(id, userName)
                                onRootNavigation.invoke(configuration)
                            }
                        )
                    )
                }

                Configuration.Status -> {
                    PagerComponent.Child.Status(
                        themeSwitcherComponent = rootModule.componentsModule.themeSwitcherComponent.value,
                        rootStatusComponent = rootModule.componentsModule.rootStatusComponent.value
                    )
                }

                Configuration.Towns -> {
                    PagerComponent.Child.Towns(
                        townsComponent = rootModule.townsModule.createTownsComponent(
                            componentContext = context
                        )
                    )
                }

                Configuration.Map -> PagerComponent.Child.Map
            }
        }
    )

    override val selectedIndex: Value<Int> = childStack.map {
        when (it.active.instance) {
            PagerComponent.Child.Map -> 3
            is PagerComponent.Child.RatingUsers -> 2
            is PagerComponent.Child.Status -> 1
            is PagerComponent.Child.Towns -> 0
        }
    }

    override fun selectStatus() {
        navigation.replaceAll(Configuration.Status)
    }

    override fun selectRatings() {
        navigation.replaceAll(Configuration.RatingUsers)
    }

    override fun selectTowns() {
        navigation.replaceAll(Configuration.Towns)
    }

    override fun selectMap() {
        navigation.replaceAll(Configuration.Map)
    }

    override fun select(index: Int) {
        when (index) {
            0 -> selectTowns()
            1 -> selectStatus()
            2 -> selectRatings()
            3 -> selectMap()
            else -> error("Index out of bounds")
        }
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        data object Status : Configuration

        @Parcelize
        data object RatingUsers : Configuration

        @Parcelize
        data object Towns : Configuration

        @Parcelize
        data object Map : Configuration
    }
}
