package com.makeevrserg.empireprojekt.mobile.features.root.di.impl

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.root.DefaultRootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.DefaultThemeSwitcherComponentComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

class RootModuleImpl : RootModule {

    override val servicesModule: ServicesModule by Single {
        ServicesModuleImpl()
    }

    override val statusModule: StatusModule by Provider {
        StatusModule.Default(
            dispatchers = servicesModule.dispatchers.value,
            httpClient = servicesModule.httpClient.value
        )
    }

    override val splashModule: SplashComponentModule by Provider {
        SplashComponentModule.Default(
            mainScope = servicesModule.mainScope.value,
            dispatchers = servicesModule.dispatchers.value
        )
    }

    override val rootStatusComponent: Single<RootStatusComponent> = Single {
        DefaultRootStatusComponent(statusModule)
    }

    override val themeSwitcherComponent: Single<ThemeSwitcherComponent> = Single {
        DefaultThemeSwitcherComponentComponent(servicesModule.settings.value)
    }
}
