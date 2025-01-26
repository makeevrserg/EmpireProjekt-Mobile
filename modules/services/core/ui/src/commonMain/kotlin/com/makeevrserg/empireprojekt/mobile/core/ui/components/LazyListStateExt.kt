package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter

@Composable
fun LazyListState.OnEndReached(block: suspend () -> Unit) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo
                .lastOrNull()
                ?: return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .filter { it }
            .collect {
                if (it) block.invoke()
            }
    }
}
