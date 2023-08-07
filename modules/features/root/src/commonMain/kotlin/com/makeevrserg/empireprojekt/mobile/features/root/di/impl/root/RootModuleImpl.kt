package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root

import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.factories.SettingsFactory
import kotlinx.coroutines.MainScope
import ru.astrainteractive.klibs.kdi.ExperimentalKDIApi
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.WiredModule
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

@OptIn(ExperimentalKDIApi::class)
internal object RootModuleImpl : RootModule, WiredModule by WiredModule.Default() {
    override val servicesModule by Single {
        ServicesModuleImpl()
    }

    override val settings = Single {
        val configuration by servicesModule.platformConfiguration
        SettingsFactory(configuration).create()
    }.remember()

    override val dispatchers = Single<KotlinDispatchers> {
        DefaultKotlinDispatchers
    }.remember()

    override val mainScope = Single {
        MainScope()
    }.remember()
}
