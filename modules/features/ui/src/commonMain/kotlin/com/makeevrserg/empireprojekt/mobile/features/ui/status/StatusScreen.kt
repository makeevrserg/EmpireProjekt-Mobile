package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.makeevrserg.empireprojekt.mobile.core.ui.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.RootComponent
import com.makeevrserg.empireprojekt.mobile.features.root.modal.RootBottomSheetComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.widget.StatusWidget
import com.makeevrserg.empireprojekt.mobile.resources.MR
import ru.astrainteractive.klibs.mikro.core.util.next

@Composable
fun StatusScreen(
    rootComponent: RootComponent,
    themeSwitcherComponent: ThemeSwitcherComponent,
    rootStatusComponent: RootStatusComponent,
) {
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navBarsPadding(),
                backgroundColor = AppTheme.materialColor.secondaryVariant,
                onClick = {
                    rootComponent.rootBottomSheetComponent.pushSlot(RootBottomSheetComponent.Child.Settings)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = AppTheme.alColors.onSecondaryVariant
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .navBarsPadding()
                .padding(AppTheme.dimens.S)
        ) {
            item {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = null,
                    tint = AppTheme.materialColor.onPrimary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            val nextTheme = themeSwitcherComponent.theme.value.next(
                                ThemeSwitcherComponent.Theme.values()
                            )
                            themeSwitcherComponent.selectTheme(nextTheme)
                        }
                )
            }
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
            items(rootStatusComponent.statusComponents) {
                StatusWidget(it)
            }
        }
    }
}
