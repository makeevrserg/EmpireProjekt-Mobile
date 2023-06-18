package com.makeevrserg.empireprojekt.mobile.features.status.data

import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext

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
