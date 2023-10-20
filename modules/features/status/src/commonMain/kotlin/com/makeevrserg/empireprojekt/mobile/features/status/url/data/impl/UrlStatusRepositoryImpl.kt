package com.makeevrserg.empireprojekt.mobile.features.status.url.data.impl

import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class UrlStatusRepositoryImpl(
    private val url: String,
    private val httpClient: HttpClient,
    private val dispatchers: KotlinDispatchers
) : UrlStatusRepository {
    override suspend fun isActive(): Result<Boolean> = runCatching {
        val request = withContext(dispatchers.IO) {
            httpClient.get(url)
        }
        request.status == HttpStatusCode.OK
    }
}
