package com.makeevrserg.empireprojekt.mobile.features.ui.status

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetRouter
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.widget.StatusWidget

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StatusScreen(
    rootBottomSheetRouter: RootBottomSheetRouter,
    themeSwitcherComponent: ThemeSwitcherComponent,
    rootStatusComponent: RootStatusComponent,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(title = MR.strings.status_title.asComposableString()) {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            themeSwitcherComponent.next()
                        }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                onClick = {
                    rootBottomSheetRouter.showInfoSheet()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = AppTheme.customColors.onSecondaryVariant
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.S)
                .navigationBarsPadding(),
            contentPadding = it
        ) {
            item {
                Text(
                    text = MR.strings.status_subtitle.asComposableString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = .5f)
                )
            }
            items(rootStatusComponent.statusComponents) {
                StatusWidget(it)
            }
        }
    }
}
