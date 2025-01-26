package com.makeevrserg.empireprojekt.mobile.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.SubcomposeAsyncImage
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun PlayerHeadBox(uuid: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = "https://mc-heads.net/avatar/$uuid",
        contentDescription = null,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmer()
                    .background(MaterialTheme.colors.primaryVariant)
                    .clip(RoundedCornerShape(AppTheme.dimens.XXS))
            )
        },
        error = {
            Icon(
                imageVector = Icons.Filled.Cookie,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    )
}
