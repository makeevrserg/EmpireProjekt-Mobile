package com.makeevrserg.empireprojekt.mobile.features.status.url

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import dev.icerock.moko.resources.desc.StringDesc

interface UrlStatusComponent : StatusComponent {
    data class Model(
        override val title: StringDesc,
        override val isLoading: Boolean,
        override val status: StatusComponent.Model.LoadingStatus
    ) : StatusComponent.Model
}
