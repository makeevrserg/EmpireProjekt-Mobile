package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root

import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.factory.SettingsFactory
import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.status.StatusModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.status.root.DefaultRootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.DefaultThemeSwitcherComponentComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import kotlinx.coroutines.MainScope
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal object RootModuleImpl : RootModule {
    override val servicesModule by Single {
        ServicesModuleImpl()
    }

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
    override val themeSwitcherComponent: Single<ThemeSwitcherComponent> = Single {
        DefaultThemeSwitcherComponentComponent(settings.value)
    }
    override val rootStatusComponent: Single<RootStatusComponent> = Single {
        DefaultRootStatusComponent(StatusModuleImpl(this))
    }
}
