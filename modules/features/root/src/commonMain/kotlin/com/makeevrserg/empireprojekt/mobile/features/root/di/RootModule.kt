package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root.RootModuleImpl
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.ExperimentalKDIApi
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.WiredModule
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

@OptIn(ExperimentalKDIApi::class)
interface RootModule : WiredModule {
    val servicesModule: ServicesModule

    val settings: Single<Settings>
    val dispatchers: Single<KotlinDispatchers>
    val mainScope: Single<CoroutineScope>

    companion object : RootModule by RootModuleImpl
}
