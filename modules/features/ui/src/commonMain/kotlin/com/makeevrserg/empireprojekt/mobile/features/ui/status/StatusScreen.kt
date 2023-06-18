package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.resources.MR

@Composable
fun StatusScreen(
    statusComponents: List<StatusComponent>,
) {
    Scaffold(
        modifier = Modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .navBarsPadding()
                .padding(AppTheme.dimens.S)
        ) {
            item {
                Text(
                    text = MR.strings.status_title.asComposableString(),
                    style = AppTheme.typography.h4,
                    color = AppTheme.materialColor.onPrimary
                )
            }
            item {
                Text(
                    text = MR.strings.status_subtitle.asComposableString(),
                    style = AppTheme.typography.body1,
                    color = AppTheme.materialColor.onPrimary.copy(alpha = .5f)
                )
            }
            items(statusComponents) {
                StatusWidget(it)
            }
        }
    }
}
