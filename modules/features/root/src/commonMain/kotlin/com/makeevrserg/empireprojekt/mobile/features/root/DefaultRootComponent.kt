package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.modal.DefaultRootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.DefaultRootScreenComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    rootModule: RootModule
) : RootComponent, ComponentContext by componentContext {
    override val rootBottomSheetComponent = DefaultRootBottomSheetComponent(
        componentContext = childContext("RootBottomSheetComponent"),
        servicesModule = rootModule.servicesModule,
    )
    override val rootScreenComponent = DefaultRootScreenComponent(
        componentContext = childContext("RootScreenComponent"),
        rootModule = rootModule,
    )
}
