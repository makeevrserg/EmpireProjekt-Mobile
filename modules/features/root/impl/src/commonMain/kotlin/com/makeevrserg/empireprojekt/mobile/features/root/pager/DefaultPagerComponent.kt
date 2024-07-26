package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.data.LastBottomItemRepository
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

internal class DefaultPagerComponent(
    componentContext: ComponentContext,
    rootModule: RootModule,
    onRootNavigation: (RootRouter.Configuration) -> Unit,
    private val lastBottomItemRepository: LastBottomItemRepository
) : PagerComponent, ComponentContext by componentContext {
    private val ratingUsersChild by lazy {
        PagerComponent.Child.RatingUsers(
            ratingUsersComponent = rootModule.ratingUsersModule.createRatingUsersComponent(
                componentContext = componentContext.childContext("RatingUsersComponent"),
                onShowUserRatingsClicked = { id, userName ->
                    val configuration = RootRouter.Configuration.RatingUser(id, userName)
                    onRootNavigation.invoke(configuration)
                }
            )
        )
    }

    private val statusChild by lazy {
        PagerComponent.Child.Status(
            themeSwitcherComponent = rootModule.themeSwitcherModule.themeSwitcherComponent,
            rootStatusComponent = rootModule.statusModule.rootStatusComponent
        )
    }

    private val townsChild by lazy {
        PagerComponent.Child.Towns(
            townsComponent = rootModule.townsModule.createTownsComponent(
                componentContext = componentContext.childContext("TownsComponent")
            )
        )
    }

    private val mapChild by lazy {
        PagerComponent.Child.Map
    }

    override val selectedBottomBarItem: StateFlow<PagerBottomBarItem>
        get() = lastBottomItemRepository.lastBottomItemIndex.cachedStateFlow

    override val selectedChild = selectedBottomBarItem.mapStateFlow {
        when (it) {
            PagerBottomBarItem.Towns -> townsChild
            PagerBottomBarItem.Status -> statusChild
            PagerBottomBarItem.Ratings -> ratingUsersChild
            PagerBottomBarItem.Map -> mapChild
        }
    }

    override fun select(item: PagerBottomBarItem) {
        lastBottomItemRepository.lastBottomItemIndex.save(item)
    }

    override fun selectMap() {
        select(PagerBottomBarItem.Map)
    }

    override fun selectRatings() {
        select(PagerBottomBarItem.Ratings)
    }

    override fun selectStatus() {
        select(PagerBottomBarItem.Status)
    }

    override fun selectTowns() {
        select(PagerBottomBarItem.Towns)
    }
}
