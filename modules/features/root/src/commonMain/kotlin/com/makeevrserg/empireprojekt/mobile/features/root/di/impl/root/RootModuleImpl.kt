package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root

import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.factories.SettingsFactory
import com.makeevrserg.mobilex.core.dispatchers.DefaultKotlinDispatchers
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.di.Single
import com.makeevrserg.mobilex.di.getValue
import kotlinx.coroutines.MainScope

internal object RootModuleImpl : RootModule {
    private val servicesModule by ServicesModule

    override val settings = Single {
        val configuration by servicesModule.platformConfiguration
        SettingsFactory(configuration).create()
    }
    override val dispatchers = Single<KotlinDispatchers> {
        DefaultKotlinDispatchers
    }
    override val mainScope = Single {
        MainScope()
    }
}
