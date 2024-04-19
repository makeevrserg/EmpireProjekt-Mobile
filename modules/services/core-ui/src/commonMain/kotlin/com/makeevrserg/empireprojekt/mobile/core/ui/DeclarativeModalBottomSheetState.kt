package com.makeevrserg.empireprojekt.mobile.core.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop

private val emptyContent: @Composable ColumnScope.() -> Unit = {
    Spacer(Modifier.height(1.dp))
}

@ExperimentalMaterialApi
class DeclarativeModalBottomSheetState(
    val sheetContent: State<@Composable ColumnScope.() -> Unit>,
    val sheetState: ModalBottomSheetState,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <C : Any, T : Any> rememberDeclarativeModalBottomSheetState(
    slot: Value<ChildSlot<C, T>>,
    onDismiss: () -> Unit,
    skipHalfExpanded: Boolean = false,
    sheetContent: @Composable (child: Child.Created<C, T>) -> Unit,
): DeclarativeModalBottomSheetState {
    val childContent = remember { mutableStateOf(emptyContent) }
    val slotState by slot.subscribeAsState()
    val child: Child.Created<C, T>? = slotState.child
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded,
        confirmValueChange = { true }
    )
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
