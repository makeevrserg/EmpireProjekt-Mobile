package com.makeevrserg.empireprojekt.mobile.features.root.pager.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.rating.users.di.RatingUsersModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.data.LastBottomItemRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface PagerModule {
    fun createPagerComponent(
        componentContext: ComponentContext,
        onRootNavigation: (RootRouter.Configuration) -> Unit
    ): PagerComponent

    class Default(
        private val ratingUsersModule: RatingUsersModule,
        private val themeSwitcherModule: ThemeSwitcherModule,
        private val statusModule: StatusModule,
        private val townsModule: TownsModule,
        private val coreModule: CoreModule
    ) : PagerModule {
        override fun createPagerComponent(
            componentContext: ComponentContext,
            onRootNavigation: (RootRouter.Configuration) -> Unit
        ): PagerComponent = DefaultPagerComponent(
            componentContext = componentContext,
            onRootNavigation = onRootNavigation,
            lastBottomItemRepository = LastBottomItemRepositoryImpl(coreModule.settings),
            themeSwitcherModule = themeSwitcherModule,
            statusModule = statusModule,
            townsModule = townsModule,
            ratingUsersModule = ratingUsersModule
        )
    }
}
