package com.makeevrserg.empireprojekt.mobile.features.ui.pager.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.makeevrserg.empireprojekt.mobile.features.ui.pager.components.PagerBottomBarItemIcon.icon

@Composable
internal fun PagerBottomBar(
    selectedIndex: Int,
    onClicked: (PagerBottomBarItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember {
        listOf(
            PagerBottomBarItem.Towns,
            PagerBottomBarItem.Status,
            PagerBottomBarItem.Ratings,
            PagerBottomBarItem.Map
        )
    }
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    onClicked.invoke(item)
                },
                icon = {
                    val tint by animateColorAsState(
                        targetValue = when {
                            isSelected -> AppTheme.customColors.astraYellow
                            else -> MaterialTheme.colors.onPrimary
                        },
                        label = "selected tint color"
                    )
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = tint
                    )
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
private fun PagerBottomBarPreview() {
    AdaptThemeFade {
        Scaffold(
            bottomBar = {
                PagerBottomBar(
                    selectedIndex = 1,
                    onClicked = {
                    }
                )
            }
        ) {
        }
    }
}
