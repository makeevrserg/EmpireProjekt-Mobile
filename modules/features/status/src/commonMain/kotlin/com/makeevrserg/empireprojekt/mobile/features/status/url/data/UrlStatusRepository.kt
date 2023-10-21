package com.makeevrserg.empireprojekt.mobile.features.status.url.data

interface UrlStatusRepository {
    suspend fun isActive(): Result<Boolean>
}
