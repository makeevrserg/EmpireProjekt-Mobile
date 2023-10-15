package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    val totalItems = layoutInfo.totalItemsCount - 4
    return lastItemIndex >= totalItems
}

@Composable
fun LazyListState.rememberIsScrolledToTheEnd(): State<Boolean> {
    return remember {
        derivedStateOf {
            this.isScrolledToTheEnd()
        }
    }
}
