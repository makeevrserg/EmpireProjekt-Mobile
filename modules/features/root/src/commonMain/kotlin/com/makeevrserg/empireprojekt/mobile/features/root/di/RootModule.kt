package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root.RootModuleImpl
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface RootModule : Module {
    val servicesModule: ServicesModule

    val settings: Single<Settings>
    val dispatchers: Single<KotlinDispatchers>
    val mainScope: Single<CoroutineScope>
    val themeSwitcherComponent: Single<ThemeSwitcherComponent>
    val rootStatusComponent: Single<RootStatusComponent>
    companion object : RootModule by RootModuleImpl
}
