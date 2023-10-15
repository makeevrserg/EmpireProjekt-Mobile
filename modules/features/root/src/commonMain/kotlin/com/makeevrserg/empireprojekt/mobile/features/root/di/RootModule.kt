package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import ru.astrainteractive.klibs.kdi.Module

interface RootModule : Module {

    val servicesModule: ServicesModule
    val statusModule: StatusModule
    val splashModule: SplashComponentModule
    val componentsModule: ComponentsModule

    val empireApiModule: EmpireApiModule
}
