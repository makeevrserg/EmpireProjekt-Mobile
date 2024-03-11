package com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl

import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.model.MinecraftStatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class MinecraftStatusRepositoryImpl(
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : UrlStatusRepository {

    override suspend fun isActive(): Result<Boolean> {
        return runCatching<MinecraftStatusRepositoryImpl, MinecraftStatusResponse> {
            val request = withContext(dispatchers.IO) {
                httpClient.get("https://api.minetools.eu/ping/play.empireprojekt.ru")
            }
            request.body()
        }.map { true }
    }
}
