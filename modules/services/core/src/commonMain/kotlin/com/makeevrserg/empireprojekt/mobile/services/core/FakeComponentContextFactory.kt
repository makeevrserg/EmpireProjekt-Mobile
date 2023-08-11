package com.makeevrserg.empireprojekt.mobile.services.core

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ru.astrainteractive.klibs.kdi.Factory

object FakeComponentContextFactory : Factory<ComponentContext> {
    override fun create(): ComponentContext = DefaultComponentContext(LifecycleRegistry())
}
