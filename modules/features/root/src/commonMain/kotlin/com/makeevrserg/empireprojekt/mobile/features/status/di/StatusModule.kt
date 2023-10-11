package com.makeevrserg.empireprojekt.mobile.features.status.di

import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface StatusModule : Module {
    val dispatchers: KotlinDispatchers
    val httpClient: HttpClient

    class Default(
        override val dispatchers: KotlinDispatchers,
        override val httpClient: HttpClient
    ) : StatusModule
}
