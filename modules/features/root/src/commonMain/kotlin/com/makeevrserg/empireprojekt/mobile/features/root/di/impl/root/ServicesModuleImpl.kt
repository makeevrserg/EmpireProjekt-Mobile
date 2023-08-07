package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root

import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.factories.LinkBrowserFactory
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.ExperimentalKDIApi
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.WiredModule
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

@OptIn(ExperimentalKDIApi::class)
internal class ServicesModuleImpl : ServicesModule, WiredModule by WiredModule.Default() {
    override val platformConfiguration = Lateinit<PlatformConfiguration>()

    override val jsonConfiguration = Single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    override val httpClient = Single {
        val jsonConfiguration by jsonConfiguration
        HttpClient {
            install(ContentNegotiation) {
                json(jsonConfiguration)
            }
        }
    }

    override val linkBrowser: Single<LinkBrowser> = Single {
        LinkBrowserFactory(platformConfiguration.value).create()
    }
}
