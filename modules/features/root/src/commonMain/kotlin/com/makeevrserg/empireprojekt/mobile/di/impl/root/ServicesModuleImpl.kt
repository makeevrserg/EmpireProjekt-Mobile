package com.makeevrserg.empireprojekt.mobile.di.impl.root

import com.makeevrserg.empireprojekt.mobile.di.ServicesModule
import com.makeevrserg.mobilex.di.Single
import com.makeevrserg.mobilex.di.getValue
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object ServicesModuleImpl : ServicesModule {
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
}
