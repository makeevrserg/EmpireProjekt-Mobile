package com.makeevrserg.empireprojekt.mobile.di.impl.root

import com.makeevrserg.empireprojekt.mobile.di.RootModule
import com.makeevrserg.empireprojekt.mobile.di.factories.SettingsFactory
import com.makeevrserg.mobilex.core.dispatchers.DefaultKotlinDispatchers
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Lateinit
import com.makeevrserg.mobilex.di.Single
import com.makeevrserg.mobilex.di.getValue
import kotlinx.coroutines.MainScope

internal object RootModuleImpl : RootModule {
    override val platformConfiguration = Lateinit<PlatformConfiguration>()

    override val settings = Single {
        val configuration by platformConfiguration
        SettingsFactory(configuration).create()
    }
    override val dispatchers = Single<KotlinDispatchers> {
        DefaultKotlinDispatchers
    }
    override val mainScope = Single {
        MainScope()
    }
}
