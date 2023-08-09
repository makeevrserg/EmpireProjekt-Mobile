package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface ServicesModule : Module {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val jsonConfiguration: Single<Json>
    val httpClient: Single<HttpClient>
    val linkBrowser: Single<LinkBrowser>
}
