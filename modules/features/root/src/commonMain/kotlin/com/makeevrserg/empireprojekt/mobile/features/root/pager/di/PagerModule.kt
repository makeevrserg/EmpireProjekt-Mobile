package com.makeevrserg.empireprojekt.mobile.features.root.pager.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.DefaultPagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.PagerComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent

interface PagerModule {
    fun createPagerComponent(
        componentContext: ComponentContext,
        onRootNavigation: (RootScreenComponent.Child) -> Unit
    ): PagerComponent

    class Default(private val rootModule: RootModule) : PagerModule {
        override fun createPagerComponent(
            componentContext: ComponentContext,
            onRootNavigation: (RootScreenComponent.Child) -> Unit
        ): PagerComponent = DefaultPagerComponent(
            componentContext = componentContext,
            rootModule = rootModule,
            onRootNavigation = onRootNavigation
        )
    }
}
