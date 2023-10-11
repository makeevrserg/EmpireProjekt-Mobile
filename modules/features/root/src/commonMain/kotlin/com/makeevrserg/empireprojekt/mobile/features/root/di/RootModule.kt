package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single

interface RootModule : Module {

    val servicesModule: ServicesModule
    val statusModule: StatusModule
    val splashModule: SplashComponentModule

    // Global components
    val rootStatusComponent: Single<RootStatusComponent>
    val themeSwitcherComponent: Single<ThemeSwitcherComponent>
}
