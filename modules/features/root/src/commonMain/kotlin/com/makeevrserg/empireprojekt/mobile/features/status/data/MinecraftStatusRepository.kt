package com.makeevrserg.empireprojekt.mobile.features.status.data

import com.makeevrserg.empireprojekt.mobile.features.status.data.model.MinecraftStatusResponse
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

interface MinecraftStatusRepository {
    suspend fun get(): Result<MinecraftStatusResponse>
    class Default(
        private val httpClient: HttpClient,
        private val dispatchers: KotlinDispatchers
    ) : MinecraftStatusRepository {
        override suspend fun get(): Result<MinecraftStatusResponse> =
            runCatching<Default, MinecraftStatusResponse> {
                val request = withContext(dispatchers.IO) {
                    httpClient.get("https://api.minetools.eu/ping/play.empireprojekt.ru")
                }
                request.body()
            }.map { it.copy(description = it.description.replace("§", "")) }
    }
}
