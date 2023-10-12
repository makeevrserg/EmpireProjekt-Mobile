package com.makeevrserg.empireprojekt.mobile.features.root.di.impl

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ComponentsModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
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

    override val componentsModule: ComponentsModule by Single {
        ComponentsModuleImpl(this)
    }
}
