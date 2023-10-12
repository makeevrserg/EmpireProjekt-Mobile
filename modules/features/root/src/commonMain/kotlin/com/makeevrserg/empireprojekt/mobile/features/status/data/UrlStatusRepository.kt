package com.makeevrserg.empireprojekt.mobile.features.status.data

interface UrlStatusRepository {
    suspend fun isActive(): Result<Boolean>
}
