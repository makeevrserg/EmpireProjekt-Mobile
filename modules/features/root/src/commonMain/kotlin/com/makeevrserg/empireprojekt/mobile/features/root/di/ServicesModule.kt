package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.ExperimentalKDIApi
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.WiredModule
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

@OptIn(ExperimentalKDIApi::class)
interface ServicesModule : WiredModule {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val jsonConfiguration: Single<Json>
    val httpClient: Single<HttpClient>
    val linkBrowser: Single<LinkBrowser>
}
