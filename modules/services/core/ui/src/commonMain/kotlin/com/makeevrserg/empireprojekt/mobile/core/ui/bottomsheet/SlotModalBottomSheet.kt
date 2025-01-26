package com.makeevrserg.empireprojekt.mobile.core.ui.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.arkivanov.decompose.router.slot.ChildSlot
import com.makeevrserg.empireprojekt.mobile.core.ui.bottomsheet.util.zero
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any, K : Any> SlotModalBottomSheet(
    childSlot: ChildSlot<T, K>,
    skipPartiallyExpanded: Boolean = true,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.(K) -> Unit
) {
    val child = childSlot.child?.instance
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = {
            true
        }
    )

    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }
            .distinctUntilChanged()
            .drop(1)
            .collect { visible ->
                if (!visible) {
                    onDismiss.invoke()
                }
            }
    }

    if (child != null) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss.invoke() },
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets.zero },
            content = {
                content.invoke(this, child)
            }
        )
    }
}
