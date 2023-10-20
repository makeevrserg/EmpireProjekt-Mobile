package com.makeevrserg.empireprojekt.mobile.features.status.url.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class MinecraftStatusResponse(
    val description: String,
    val players: Players
) {
    @Serializable
    data class Players(
        val max: Int,
        val online: Int
    )
}
