package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.makeevrserg.empireprojekt.mobile.core.ui.bottomsheet.SlotModalBottomSheet
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.info.InfoScreen

@Composable
fun RootBottomSheetContent(
    rootBottomSheetComponent: RootBottomSheetComponent,
) {
    val childSlot by rootBottomSheetComponent.childSlot.subscribeAsState()

    SlotModalBottomSheet(
        childSlot = childSlot,
        onDismiss = rootBottomSheetComponent::dismiss,
        content = { child ->
            when (child) {
                is RootBottomSheetComponent.Child.Info -> InfoScreen(child.linkBrowser)
            }
        }
    )
}
