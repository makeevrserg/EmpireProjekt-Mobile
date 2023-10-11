package com.makeevrserg.empireprojekt.mobile.features.root.di.impl

import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.factory.LinkBrowserFactory
import com.makeevrserg.empireprojekt.mobile.features.root.di.factory.SettingsFactory
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal class ServicesModuleImpl : ServicesModule {

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

    override val settings = Single {
        val configuration by platformConfiguration
        SettingsFactory(configuration).create()
    }

    override val dispatchers = Single<KotlinDispatchers> {
        DefaultKotlinDispatchers
    }

    override val mainScope = Single {
        MainScope()
    }
}
