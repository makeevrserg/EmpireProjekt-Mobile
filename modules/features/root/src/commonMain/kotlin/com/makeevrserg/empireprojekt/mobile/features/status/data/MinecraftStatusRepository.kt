package com.makeevrserg.empireprojekt.mobile.features.status.data

import com.makeevrserg.empireprojekt.mobile.features.status.data.model.MinecraftStatusResponse

interface MinecraftStatusRepository {
    suspend fun get(): Result<MinecraftStatusResponse>
}
