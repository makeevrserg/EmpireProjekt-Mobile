package com.makeevrserg.empireprojekt.mobile.wear.tile.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.Button
import androidx.wear.protolayout.material.ButtonColors
import com.google.android.horologist.compose.tools.LayoutRootPreview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.features.status.WearStatusComponent
import ru.astrainteractive.klibs.kdi.Factory
import java.util.UUID

class StatusesRowFactory(
    private val context: Context,
    private val wearStatusComponent: WearStatusComponent,
    private val theme: AppTheme
) : Factory<LayoutElementBuilders.LayoutElement> {

    private fun statusButton(
        context: Context,
        amount: Int,
        theme: AppTheme,
        accentColor: Color,
    ): Button {
        return Button.Builder(
            context,
            ModifiersBuilders.Clickable.Builder()
                .setId(UUID.randomUUID().toString())
                .setOnClick(ActionBuilders.LoadAction.Builder().build())
                .build()
        )
            .setButtonColors(
                ButtonColors(
                    theme.materialColor.primary.toArgb(),
                    accentColor.toArgb()
                )
            )
            .setTextContent(amount.toString()).build()
    }

    override fun create(): LayoutElementBuilders.Row {
        return LayoutElementBuilders.Row.Builder()
            .setWidth(DimensionBuilders.wrap())
            .setVerticalAlignment(LayoutElementBuilders.VERTICAL_ALIGN_CENTER)
            .addContent(
                statusButton(
                    context = context,
                    amount = wearStatusComponent.mergedState.value.successCount,
                    accentColor = theme.alColors.colorPositive,
                    theme = theme
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    context = context,
                    amount = wearStatusComponent.mergedState.value.loadingCount,
                    accentColor = theme.alColors.astraOrange,
                    theme = theme
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    context = context,
                    amount = wearStatusComponent.mergedState.value.failureCount,
                    accentColor = theme.alColors.colorNegative,
                    theme = theme
                )
            ).build()
    }
}

@DefaultPreview
@Composable
private fun StatusesComponentFactoryPreview() {
    LayoutRootPreview(
        root = StatusesRowFactory(
            LocalContext.current,
            WearStatusComponent.Stub(),
            AppTheme.DefaultDarkTheme
        ).create()
    )
}
