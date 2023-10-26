package com.makeevrserg.empireprojekt.mobile.features.status.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl.MinecraftStatusRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl.UrlStatusRepositoryImpl
import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.kdi.Module
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

interface StatusModule : Module {
    val dispatchers: KotlinDispatchers
    val minecraftStatusRepository: UrlStatusRepository
    val storeFactory: StoreFactory
    fun urlStatRepositoryFactory(url: String): Factory<UrlStatusRepository>
    class Default(
        override val dispatchers: KotlinDispatchers,
        private val httpClient: HttpClient
    ) : StatusModule {
        override val minecraftStatusRepository: UrlStatusRepository by Provider {
            MinecraftStatusRepositoryImpl(
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }
        override val storeFactory: StoreFactory = DefaultStoreFactory()
        override fun urlStatRepositoryFactory(url: String): Factory<UrlStatusRepository> = Factory {
            UrlStatusRepositoryImpl(
                url = url,
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }
    }
}
