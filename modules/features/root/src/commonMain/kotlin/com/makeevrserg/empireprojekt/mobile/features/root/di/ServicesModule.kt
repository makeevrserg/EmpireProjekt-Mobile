package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface ServicesModule : Module {

    val platformConfiguration: Lateinit<PlatformConfiguration>
    val jsonConfiguration: Single<Json>
    val httpClient: Single<HttpClient>
    val linkBrowser: Single<LinkBrowser>
    val settings: Single<Settings>
    val dispatchers: Single<KotlinDispatchers>
    val mainScope: Single<CoroutineScope>
}
