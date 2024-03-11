package com.makeevrserg.empireprojekt.mobile.features.root.di.impl

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ComponentsModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

class RootModuleImpl : RootModule {

    override val servicesModule: ServicesModule by Single {
        ServicesModuleImpl()
    }

    override val empireApiModule: EmpireApiModule by Provider {
        EmpireApiModule.Default(
            httpClient = servicesModule.httpClient.value
        )
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

    override val townsModule: TownsModule by Provider {
        TownsModule.Default(
            empireApiModule = empireApiModule,
            dispatchers = servicesModule.dispatchers.value,
            settings = servicesModule.settings.value
        )
    }
    override val pagerModule: PagerModule by Provider {
        PagerModule.Default(this)
    }

    override val componentsModule: ComponentsModule by Single {
        ComponentsModuleImpl(this)
    }
}
