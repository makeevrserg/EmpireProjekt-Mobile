package com.makeevrserg.empireprojekt.mobile.features.root.di.impl

import com.makeevrserg.empireprojekt.mobile.features.root.di.ComponentsModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.status.root.DefaultRootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.DefaultThemeSwitcherComponentComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.di.ThemeSwitcherModule
import ru.astrainteractive.klibs.kdi.Single

class ComponentsModuleImpl(
    rootModule: RootModule
) : ComponentsModule {
    override val rootStatusComponent: Single<RootStatusComponent> = Single {
        DefaultRootStatusComponent(rootModule.statusModule)
    }

    override val themeSwitcherComponent: Single<ThemeSwitcherComponent> = Single {
        val module = ThemeSwitcherModule.Default(
            settings = rootModule.servicesModule.settings.value
        )
        DefaultThemeSwitcherComponentComponent(module)
    }
}
