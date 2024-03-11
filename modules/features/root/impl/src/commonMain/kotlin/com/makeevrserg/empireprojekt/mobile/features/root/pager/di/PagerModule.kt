package com.makeevrserg.empireprojekt.mobile.features.root.pager.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootRouter

interface PagerModule {
    fun createPagerComponent(
        componentContext: ComponentContext,
        onRootNavigation: (RootRouter.Configuration) -> Unit
    ): PagerComponent

    class Default(private val rootModule: RootModule) : PagerModule {
        override fun createPagerComponent(
            componentContext: ComponentContext,
            onRootNavigation: (RootRouter.Configuration) -> Unit
        ): PagerComponent = DefaultPagerComponent(
            componentContext = componentContext,
            rootModule = rootModule,
            onRootNavigation = onRootNavigation
        )
    }
}
