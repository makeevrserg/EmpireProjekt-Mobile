package com.makeevrserg.empireprojekt.mobile.wear.tile.components

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.Button
import androidx.wear.protolayout.material.ButtonColors
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent
import java.util.UUID

@OptIn(ExperimentalHorologistApi::class)
class StatusesRowRenderer(
    context: Context,
) : SingleTileLayoutRenderer<WearStatusComponent.Model, WearStatusComponent.Model>(context) {

    private fun statusButton(
        amount: Int,
        accentColor: Color,
    ): LayoutElementBuilders.LayoutElement {
        return Button.Builder(
            context,
            ModifiersBuilders.Clickable.Builder()
                .setId(UUID.randomUUID().toString())
                .setOnClick(ActionBuilders.LoadAction.Builder().build())
                .build()
        ).setButtonColors(
            ButtonColors(
                theme.surface,
                accentColor.toArgb()
            )
        ).setTextContent(amount.toString()).build()
    }

    override fun renderTile(
        state: WearStatusComponent.Model,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return LayoutElementBuilders.Row.Builder()
            .setWidth(DimensionBuilders.wrap())
            .setVerticalAlignment(LayoutElementBuilders.VERTICAL_ALIGN_CENTER)
            .addContent(
                statusButton(
                    amount = state.successCount,
                    accentColor = AppTheme().customColors.colorPositive,
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    amount = state.loadingCount,
                    accentColor = AppTheme().customColors.astraOrange,
                )
            )
            .addContent(
                LayoutElementBuilders.Box.Builder().setWidth(DimensionBuilders.dp(8f)).setHeight(
                    DimensionBuilders.expand()
                ).build()
            )
            .addContent(
                statusButton(
                    amount = state.failureCount,
                    accentColor = AppTheme().customColors.colorNegative,
                )
            ).build()
    }
}
