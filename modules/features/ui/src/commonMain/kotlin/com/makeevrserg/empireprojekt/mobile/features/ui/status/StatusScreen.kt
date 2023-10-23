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
import androidx.compose.ui.graphics.Color
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.RowSettingChevronItem
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.screen.RootScreenComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.features.ui.status.widget.StatusWidget
import com.makeevrserg.empireprojekt.mobile.resources.MR
import ru.astrainteractive.klibs.mikro.core.util.next

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StatusScreen(
    rootComponent: RootComponent,
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
                            val nextTheme = themeSwitcherComponent.theme.value.next(
                                Theme.values()
                            )
                            themeSwitcherComponent.selectTheme(nextTheme)
                        }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navBarsPadding(),
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                onClick = {
                    rootComponent.rootBottomSheetComponent.showInfoSheet()
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
            modifier = Modifier.padding(horizontal = AppTheme.dimens.S).navigationBarsPadding(),
            contentPadding = it
        ) {
            item {
                Text(
                    text = MR.strings.status_subtitle.asComposableString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = .5f)
                )
            }
            item {
                RowSettingChevronItem(
                    icon = MR.images.ic_splash,
                    text = MR.strings.statuses_ratings.asComposableString(),
                    tint = Color.Unspecified,
                    onClick = {
                        val configuration = RootScreenComponent.Child.RatingUsers
                        rootComponent.rootScreenComponent.push(configuration)
                    }
                )
            }
            items(rootStatusComponent.statusComponents) {
                StatusWidget(it)
            }
        }
    }
}
