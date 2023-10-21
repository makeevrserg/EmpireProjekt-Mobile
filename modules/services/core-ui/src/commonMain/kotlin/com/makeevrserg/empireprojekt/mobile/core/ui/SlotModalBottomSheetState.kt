@file:Suppress("Filename")

package com.makeevrserg.empireprojekt.mobile.core.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop

private val emptyContent: @Composable ColumnScope.() -> Unit = {}

@ExperimentalMaterialApi
class DeclarativeModalBottomSheetState(
    val sheetContent: State<@Composable ColumnScope.() -> Unit>,
    val sheetState: ModalBottomSheetState,
)

@ExperimentalMaterialApi
@Composable
fun <T : Any> rememberDeclarativeModalBottomSheetState(
    child: T?,
    onDismiss: () -> Unit,
    skipHalfExpanded: Boolean = false,
    sheetContent: @Composable (child: T) -> Unit,
): DeclarativeModalBottomSheetState {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded,
    )
    val childContent = remember { mutableStateOf(emptyContent) }

    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }
            .distinctUntilChanged()
            .drop(1)
            .collect { visible ->
                if (visible.not()) {
                    onDismiss()
                }
            }
    }

    LaunchedEffect(child == null) {
        if (child == null) {
            sheetState.hide()
            childContent.value = emptyContent
        } else {
            sheetState.show()
        }
    }

    DisposableEffect(child) {
        if (child != null) {
            childContent.value = { sheetContent(child) }
        }
        onDispose {}
    }

    return remember {
        DeclarativeModalBottomSheetState(
            sheetContent = childContent,
            sheetState = sheetState
        )
    }
}
