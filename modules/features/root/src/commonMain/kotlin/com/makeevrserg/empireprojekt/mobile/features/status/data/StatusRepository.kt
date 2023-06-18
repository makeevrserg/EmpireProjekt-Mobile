package com.makeevrserg.empireprojekt.mobile.features.status.data

interface StatusRepository {
    suspend fun isActive(): Result<Boolean>
}
