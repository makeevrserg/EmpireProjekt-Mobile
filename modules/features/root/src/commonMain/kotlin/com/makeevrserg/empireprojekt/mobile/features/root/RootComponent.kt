package com.makeevrserg.empireprojekt.mobile.features.root

import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent

interface RootComponent {
    val rootScreenComponent: RootScreenComponent
    val rootBottomSheetComponent: RootBottomSheetComponent
}
