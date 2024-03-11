package com.makeevrserg.empireprojekt.mobile.features.root.screen

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

interface RootScreenComponent : BackHandlerOwner, PopComponent, RootRouter {
    val childStack: Value<ChildStack<*, DefaultRootScreenComponent.Configuration>>
}
