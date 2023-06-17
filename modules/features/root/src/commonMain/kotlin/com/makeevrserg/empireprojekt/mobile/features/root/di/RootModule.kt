package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root.RootModuleImpl
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Lateinit
import com.makeevrserg.mobilex.di.Module
import com.makeevrserg.mobilex.di.Single
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope

interface RootModule : Module {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val settings: Single<Settings>
    val dispatchers: Single<KotlinDispatchers>
    val mainScope: Single<CoroutineScope>

    companion object : RootModule by RootModuleImpl
}
