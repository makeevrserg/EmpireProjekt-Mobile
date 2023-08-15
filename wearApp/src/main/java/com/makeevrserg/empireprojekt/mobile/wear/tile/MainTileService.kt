package com.makeevrserg.empireprojekt.mobile.wear.tile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.ColorBuilders
import androidx.wear.protolayout.ColorBuilders.ColorProp
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.dp
import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.TitleChip
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.tools.LayoutRootPreview
import com.google.android.horologist.compose.tools.buildDeviceParameters
import com.google.android.horologist.tiles.SuspendingTileService
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcher
import com.makeevrserg.empireprojekt.mobile.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.MainActivity
import com.makeevrserg.empireprojekt.mobile.wear.di.WearRootModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.kdi.getValue

private const val RESOURCES_VERSION = "1"

/**
 * Skeleton for a tile with no images.
 */
@OptIn(ExperimentalHorologistApi::class)
class MainTileService : SuspendingTileService() {

    private val rootModule by WearRootModule
    private val themeSwitcher by rootModule.themeSwitcher

    private val appTheme: AppTheme
        get() = when (themeSwitcher.theme.value) {
            ThemeSwitcher.Theme.DARK -> AppTheme.DefaultDarkTheme
            ThemeSwitcher.Theme.LIGHT -> AppTheme.DefaultLightTheme
        }

    override fun onCreate() {
        super.onCreate()
        lifecycleScope.launch {
            while (isActive) {
                delay(1000L)
            }
        }
    }

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ResourceBuilders.Resources {
        return ResourceBuilders.Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .addIdToImageMapping(
                R.drawable.esmptelegram::class.simpleName!!,
                ResourceBuilders.ImageResource.Builder()
                    .setAndroidResourceByResId(
                        ResourceBuilders.AndroidImageResourceByResId.Builder()
                            .setResourceId(R.drawable.esmptelegram)
                            .build()
                    ).build()
            )
            .build()
    }

    override suspend fun tileRequest(
        requestParams: RequestBuilders.TileRequest
    ): TileBuilders.Tile {
        val layout = LayoutElementBuilders.Layout.Builder()
            .setRoot(tileLayout(this, appTheme))
            .build()
        val entry = TimelineBuilders.TimelineEntry.Builder()
            .setLayout(layout)
            .build()
        val singleTileTimeline = TimelineBuilders.Timeline.Builder()
            .addTimelineEntry(entry)
            .build()

        return TileBuilders.Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setFreshnessIntervalMillis(1000L)
            .setTileTimeline(singleTileTimeline)
            .build()
    }
}

val Color.colorProp: ColorBuilders.ColorProp
    get() = ColorProp.Builder()
        .setArgb(this.toArgb())
        .build()

private var lastTimeUpdated = System.currentTimeMillis()
private var prevTimeUpdated = lastTimeUpdated

private fun tileLayout(context: Context, theme: AppTheme): LayoutElementBuilders.LayoutElement {
    prevTimeUpdated = lastTimeUpdated
    lastTimeUpdated = System.currentTimeMillis()
    val image = LayoutElementBuilders.Image.Builder()
        .setWidth(dp(24f))
        .setHeight(dp(24f))
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
        .setColor(theme.materialColor.onPrimary.colorProp)
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

    val titleChip = TitleChip.Builder(
        context,
        "Open statuses",
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
        DeviceParameters.Builder().build()
    ).setWidth(expand()).setChipColors(
        ChipColors(
            theme.materialColor.primaryVariant.colorProp,
            theme.materialColor.onPrimary.colorProp,
            theme.materialColor.onPrimary.colorProp,
            theme.materialColor.primary.colorProp,
        )
    ).build()

    val column = LayoutElementBuilders.Column.Builder()
        .setWidth(expand())
        .addContent(image)
        .addContent(text)
        .addContent(titleChip)
        .build()

    return PrimaryLayout.Builder(buildDeviceParameters(context.resources))
        .setContent(column)
        .build()
}

@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true
)
@Composable
fun TilePreview() {
    LayoutRootPreview(root = tileLayout(LocalContext.current, AppTheme.DefaultDarkTheme))
}
