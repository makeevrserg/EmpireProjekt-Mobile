package com.makeevrserg.empireprojekt.mobile.features.status.data.impl

import com.makeevrserg.empireprojekt.mobile.features.status.data.MinecraftStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.data.model.MinecraftStatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

class MinecraftStatusRepositoryImpl(
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : MinecraftStatusRepository {
    override suspend fun get(): Result<MinecraftStatusResponse> =
        runCatching<MinecraftStatusRepositoryImpl, MinecraftStatusResponse> {
            val request = withContext(dispatchers.IO) {
                httpClient.get("https://api.minetools.eu/ping/play.empireprojekt.ru")
            }
            request.body()
        }.map { it.copy(description = it.description.replace("§", "")) }
}
