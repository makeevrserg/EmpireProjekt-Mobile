package com.makeevrserg.empireprojekt.mobile.wear.tile.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.tools.TileLayoutPreview
import com.google.android.horologist.compose.tools.buildDeviceParameters
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.makeevrserg.empireprojekt.mobile.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.MainActivity
import com.makeevrserg.empireprojekt.mobile.wear.features.status.presentation.WearStatusComponent
import com.makeevrserg.empireprojekt.mobile.wear.tile.asColorProp

@OptIn(ExperimentalHorologistApi::class)
class MainTileRenderer(
    context: Context,
) : SingleTileLayoutRenderer<WearStatusComponent.Model, WearStatusComponent.Model>(context) {
    @Suppress("LongMethod")
    private fun tileLayout(
        state: WearStatusComponent.Model,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        val image = LayoutElementBuilders.Image.Builder()
            .setWidth(DimensionBuilders.dp(24f))
            .setHeight(DimensionBuilders.dp(24f))
            .setResourceId(R.drawable.esmptelegram::class.simpleName!!)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setSemantics(
                        ModifiersBuilders.Semantics.Builder()
                            .setContentDescription("Image description")
                            .build()
                    )
                    .build()
            ).build()
        val text = Text.Builder(context, "Empire Network Status")
            .setTypography(Typography.TYPOGRAPHY_CAPTION1)
            .setColor(theme.onSurface.asColorProp)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setClickable(
                        ModifiersBuilders.Clickable.Builder()
                            .setId("reload")
                            .setOnClick(ActionBuilders.LoadAction.Builder().build())
                            .build()
                    ).build()
            )
            .build()

        val textTimeStamp = Text.Builder(context, state.updatedAt)
            .setTypography(Typography.TYPOGRAPHY_CAPTION1)
            .setColor(theme.onSurface.asColorProp)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setClickable(
                        ModifiersBuilders.Clickable.Builder()
                            .setId("reload")
                            .setOnClick(ActionBuilders.LoadAction.Builder().build())
                            .build()
                    ).build()
            )
            .build()
        val compactChip = CompactChip.Builder(
            context,
            "More",
            ModifiersBuilders.Clickable.Builder()
                .setId("openmain")
                .setOnClick(
                    ActionBuilders.LaunchAction.Builder()
                        .setAndroidActivity(
                            ActionBuilders.AndroidActivity.Builder()
                                .setClassName(MainActivity::class.java.name)
                                .setPackageName(context.packageName)
                                .build()
                        ).build()
                ).build(),
            deviceParameters
        ).setChipColors(ChipColors(theme.surface, theme.onSurface)).build()

        val statuses = StatusesRowRenderer(context).renderTile(state, deviceParameters)

        val column = LayoutElementBuilders.Column.Builder()
            .setHorizontalAlignment(LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER)
            .setWidth(DimensionBuilders.expand())
            .addContent(image)
            .addContent(text)
            .addContent(textTimeStamp)
            .addContent(statuses)
            .build()

        return PrimaryLayout.Builder(buildDeviceParameters(context.resources))
            .setContent(column)
            .setPrimaryChipContent(compactChip)
            .build()
    }

    override fun renderTile(
        state: WearStatusComponent.Model,
        deviceParameters: DeviceParametersBuilders.DeviceParameters
    ): LayoutElementBuilders.LayoutElement {
        return tileLayout(state, deviceParameters)
    }
}

@OptIn(ExperimentalHorologistApi::class)
@DefaultPreview
@Composable
fun TilePreview() {
    val context = LocalContext.current
    val renderer = remember { MainTileRenderer(context) }
    TileLayoutPreview(WearStatusComponent.Model(), WearStatusComponent.Model(), renderer)
}
