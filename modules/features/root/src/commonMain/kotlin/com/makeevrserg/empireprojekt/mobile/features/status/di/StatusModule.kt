package com.makeevrserg.empireprojekt.mobile.features.status.di

import com.makeevrserg.empireprojekt.mobile.features.status.data.MinecraftStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.data.impl.MinecraftStatusRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.status.data.impl.UrlStatusRepositoryImpl
import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface StatusModule : Module {
    val dispatchers: KotlinDispatchers
    val minecraftStatusRepository: MinecraftStatusRepository
    fun urlStatRepositoryFactory(url: String): Factory<UrlStatusRepository>
    class Default(
        override val dispatchers: KotlinDispatchers,
        private val httpClient: HttpClient
    ) : StatusModule {
        override val minecraftStatusRepository: MinecraftStatusRepository by Provider {
            MinecraftStatusRepositoryImpl(
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }
        override fun urlStatRepositoryFactory(url: String) = Factory {
            UrlStatusRepositoryImpl(
                url = url,
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }
    }
}
