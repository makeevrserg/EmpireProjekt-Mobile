@file:Suppress("MagicNumber")

package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Collections

@Composable
fun AstraLoading(size: Dp = 64.dp) {
    var position by remember { mutableStateOf(1) }
    LaunchedEffect(key1 = "Animation") {
        launch {
            while (true) {
                delay(80 - (position % 8) * 4L)
                position += 1
            }
        }
    }
    val list = (0..7).map { it / 5f }

    @Composable
    fun BoxItem(cp: Int, i: Int, color: Color = AppTheme.customColors.astraRed) {
        val mutableList = list.toMutableList()
        Collections.rotate(mutableList, cp)
        val visibility: Float = mutableList[i]
        Box(
            Modifier
                .size(size)
                .padding(2.dp)
                .scale(visibility.coerceIn(0f, 1f))
                .background(
                    color,
                    RoundedCornerShape(2.dp)
                )
        )
    }
    // 8/8 1/8 2/8
    // 7/8     3/8
    // 6/8 5/8 4/8
    // 8 1 2
    // 7   3
    // 6 5 4
    Column(Modifier.width(size * 3), horizontalAlignment = Alignment.CenterHorizontally) {
        val r = (position % 8)
        Column {
            Row(Modifier.fillMaxWidth()) {
                BoxItem(r, 7, AppTheme.customColors.astraRed)
                BoxItem(r, 0, AppTheme.customColors.astraBlue)
                BoxItem(r, 1, AppTheme.customColors.astraOrange)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                BoxItem(r, 6, AppTheme.customColors.astraRed)
//                BoxItem(r, 8, MaterialTheme.colors.astraRed)
                BoxItem(r, 2, AppTheme.customColors.astraYellow)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                BoxItem(r, 5)
                BoxItem(r, 4)
                BoxItem(r, 3)
            }
        }
    }
}
