package com.makeevrserg.empireprojekt.mobile.wear.features.status

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.StubStatusComponent

interface WearStatusComponent {
    val statuses: List<StatusComponent>

    class Stub : WearStatusComponent {
        override val statuses: List<StatusComponent> = List(10) {
            StubStatusComponent()
        }
    }
}
