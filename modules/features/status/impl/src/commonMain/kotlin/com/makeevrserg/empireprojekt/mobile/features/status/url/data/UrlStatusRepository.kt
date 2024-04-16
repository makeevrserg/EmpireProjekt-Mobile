package com.makeevrserg.empireprojekt.mobile.features.status.url.data

internal interface UrlStatusRepository {
    suspend fun isActive(): Result<Boolean>
}
