package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.makeevrserg.empireprojekt.mobile.features.rating.users.DefaultRatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter

internal class DefaultPagerComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
    onRootNavigation: (RootRouter.Configuration) -> Unit
) : PagerComponent, ComponentContext by componentContext {
    private val ratingUsersChild by lazy {
        PagerComponent.Child.RatingUsers(
            ratingUsersComponent = DefaultRatingUsersComponent(
                componentContext = componentContext.childContext("RatingUsers"),
                moduleFactory = {
                    RatingUsersModule.Default(
                        empireApiModule = rootModule.empireApiModule,
                        dispatchers = rootModule.servicesModule.dispatchers.value
                    )
                },
                onShowUserRatingsClicked = { id, userName ->
                    val configuration = RootRouter.Configuration.RatingUser(id, userName)
                    onRootNavigation.invoke(configuration)
                }
            )
        )
    }

    private val statusChild by lazy {
        PagerComponent.Child.Status(
            themeSwitcherComponent = rootModule.componentsModule.themeSwitcherComponent.value,
            rootStatusComponent = rootModule.componentsModule.rootStatusComponent.value
        )
    }

    private val townsChild by lazy {
        PagerComponent.Child.Towns(
            townsComponent = rootModule.townsModule.createTownsComponent(
                componentContext = componentContext.childContext("Towns")
            )
        )
    }

    private val mapChild by lazy {
        PagerComponent.Child.Map
    }

    override val selectedIndex: MutableValue<Int> = MutableValue(1)

    override val selectedChild: Value<PagerComponent.Child> = selectedIndex.map {
        when (it) {
            0 -> townsChild
            1 -> statusChild
            2 -> ratingUsersChild
            3 -> mapChild
            else -> error("Index out of bounds")
        }
    }

    override fun select(index: Int) {
        selectedIndex.value = index
    }
}
