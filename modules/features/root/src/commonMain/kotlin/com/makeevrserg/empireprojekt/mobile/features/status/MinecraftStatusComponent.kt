package com.makeevrserg.empireprojekt.mobile.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.data.model.MinecraftStatusResponse
import com.makeevrserg.empireprojekt.mobile.services.core.AnyStateFlow
import dev.icerock.moko.resources.desc.StringDesc

interface MinecraftStatusComponent : StatusComponent {
    override val model: AnyStateFlow<Model>

    data class Model(
        override val title: StringDesc,
        override val isLoading: Boolean,
        override val status: StatusComponent.Model.LoadingStatus,
        val statusResult: Result<MinecraftStatusResponse>
    ) : StatusComponent.Model
}
