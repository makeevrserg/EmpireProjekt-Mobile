package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.core.ui.rememberDeclarativeModalBottomSheetState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.info.InfoScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootBottomSheetContent(
    rootBottomSheetComponent: RootBottomSheetComponent,
    content: @Composable () -> Unit
) {
    val bottomSheetState = rememberDeclarativeModalBottomSheetState(
        slot = rootBottomSheetComponent.childSlot,
        onDismiss = rootBottomSheetComponent::dismiss
    ) { child ->
        when (val instance = child.instance) {
            is RootBottomSheetComponent.Child.Info -> {
                InfoScreen(instance.linkBrowser)
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState.sheetState,
        sheetContent = bottomSheetState.sheetContent.value,
        sheetShape = RoundedCornerShape(AppTheme.dimens.S),
        sheetBackgroundColor = MaterialTheme.colors.primary,
        content = content
    )
}
