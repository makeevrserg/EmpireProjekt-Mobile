package com.makeevrserg.empireprojekt.mobile.services.core.di

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.makeevrserg.empireprojekt.mobile.services.core.di.factory.LinkBrowserFactory
import com.makeevrserg.empireprojekt.mobile.services.core.di.factory.SettingsFactory
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface CoreModule {

    val platformConfiguration: Lateinit<PlatformConfiguration>
    val jsonConfiguration: Json
    val httpClient: HttpClient
    val linkBrowser: LinkBrowser
    val settings: Settings
    val dispatchers: KotlinDispatchers
    val mainScope: CoroutineScope

    class Default : CoreModule {

        override val platformConfiguration = Lateinit<PlatformConfiguration>()

        override val jsonConfiguration by lazy {
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        override val httpClient by lazy {
            HttpClient {
                install(ContentNegotiation) {
                    json(jsonConfiguration)
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.HEADERS
                }
            }
        }

        override val linkBrowser by lazy {
            LinkBrowserFactory(platformConfiguration.value).create()
        }

        override val settings by lazy {
            val configuration by platformConfiguration
            SettingsFactory(configuration).create()
        }

        override val dispatchers by lazy {
            DefaultKotlinDispatchers
        }

        override val mainScope by lazy {
            MainScope()
        }
    }
}
