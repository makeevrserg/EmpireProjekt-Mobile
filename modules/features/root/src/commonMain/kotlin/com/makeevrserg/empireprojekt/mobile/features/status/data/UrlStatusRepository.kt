package com.makeevrserg.empireprojekt.mobile.features.status.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

class UrlStatusRepository(
    private val url: String,
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : StatusRepository {
    override suspend fun isActive(): Result<Boolean> = runCatching {
        val request = withContext(dispatchers.IO) {
            httpClient.get(url)
        }
        request.status == HttpStatusCode.OK
    }
}
