package com.makeevrserg.empireprojekt.mobile.features.status.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl.MinecraftStatusRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl.UrlStatusRepositoryImpl
import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal interface RootStatusComponentDependencies {
    val dispatchers: KotlinDispatchers
    val minecraftStatusRepository: UrlStatusRepository
    val storeFactory: StoreFactory
    fun createUrlStatusRepository(url: String): UrlStatusRepository

    class Default(
        override val dispatchers: KotlinDispatchers,
        override val storeFactory: StoreFactory,
        private val httpClient: HttpClient
    ) : RootStatusComponentDependencies {
        override val minecraftStatusRepository: UrlStatusRepository by lazy {
            MinecraftStatusRepositoryImpl(
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }

        override fun createUrlStatusRepository(url: String): UrlStatusRepository {
            return UrlStatusRepositoryImpl(
                url = url,
                httpClient = httpClient,
                dispatchers = dispatchers
            )
        }
    }
}
