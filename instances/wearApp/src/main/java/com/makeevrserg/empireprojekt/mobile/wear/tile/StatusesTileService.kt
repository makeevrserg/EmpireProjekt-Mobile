package com.makeevrserg.empireprojekt.mobile.wear.tile

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import androidx.wear.protolayout.ColorBuilders
import androidx.wear.protolayout.ColorBuilders.ColorProp
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import androidx.wear.tiles.TileService
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.SuspendingTileService
import com.makeevrserg.empireprojekt.mobile.core.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.application.App.Companion.asEmpireApp
import com.makeevrserg.empireprojekt.mobile.wear.tile.components.MainTileRenderer
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

private const val RESOURCES_VERSION = "1"

/**
 * Skeleton for a tile with no images.
 */
@OptIn(ExperimentalHorologistApi::class)
class StatusesTileService : SuspendingTileService() {

    private val wearRootModule by Provider {
        application.asEmpireApp().wearRootModule
    }
    private val wearStatusComponent by Provider {
        wearRootModule.wearStatusComponent.value
    }
    private val mainTileRenderer by Single {
        MainTileRenderer(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        lifecycleScope.launch {
            while (isActive) {
                delay(1000L)
                TileService.getUpdater(applicationContext).requestUpdate(StatusesTileService::class.java)
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
        val state = wearStatusComponent.mergedState.value
        val layout = LayoutElementBuilders.Layout.Builder()
            .setRoot(mainTileRenderer.renderTile(state, requestParams.deviceConfiguration))
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

val Int.asColorProp: ColorBuilders.ColorProp
    get() = ColorProp.Builder()
        .setArgb(this)
        .build()
