package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.di.EmpireApiModule
import com.makeevrserg.empireprojekt.mobile.features.logic.splash.di.SplashComponentModule
import com.makeevrserg.empireprojekt.mobile.features.root.pager.di.PagerModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.di.TownsModule
import ru.astrainteractive.klibs.kdi.Module

interface RootModule : Module {

    val servicesModule: ServicesModule
    val statusModule: StatusModule
    val splashModule: SplashComponentModule
    val townsModule: TownsModule
    val componentsModule: ComponentsModule
    val pagerModule: PagerModule
    val empireApiModule: EmpireApiModule
}
