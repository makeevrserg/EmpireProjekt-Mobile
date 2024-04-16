package com.makeevrserg.empireprojekt.mobile.features.ui.pager.components.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.NetworkWifi
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class PagerBottomBarItem(val icon: ImageVector) {
    data object Towns : PagerBottomBarItem(icon = Icons.Filled.Apartment)
    data object Status : PagerBottomBarItem(icon = Icons.Filled.NetworkWifi)
    data object Ratings : PagerBottomBarItem(icon = Icons.Filled.People)
    data object Map : PagerBottomBarItem(icon = Icons.Filled.Map)
}
