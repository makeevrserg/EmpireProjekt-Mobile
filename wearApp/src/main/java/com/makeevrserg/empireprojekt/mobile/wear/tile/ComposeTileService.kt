package com.makeevrserg.empireprojekt.mobile.wear.tile

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.wear.tiles.GlanceTileService
import androidx.glance.wear.tiles.LocalTimeInterval
import androidx.glance.wear.tiles.TimeInterval
import androidx.glance.wear.tiles.TimelineMode
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.TitleChip
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.resources.R
import com.makeevrserg.empireprojekt.mobile.wear.MainActivity
import java.time.Instant
import java.util.UUID

/**
 * Just a sample
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:glance/glance-wear-tiles/integration-tests/demos/src/main/java/androidx/glance/wear/tiles/demos/CalendarTileService.kt
 */
class ComposeTileService : GlanceTileService() {
    private val timeInstant = Instant.now()
    override val timelineMode = TimelineMode.TimeBoundEntries(
        setOf(
            TimeInterval(),
            TimeInterval(
                timeInstant,
                timeInstant.plusSeconds(60)
            ),
            TimeInterval(
                timeInstant.plusSeconds(60),
                timeInstant.plusSeconds(120)
            )
        )
    )

    @Composable
    private fun Chip(text: String, theme: AppTheme): TitleChip {
        val context = LocalContext.current
        return TitleChip.Builder(
            context,
            text,
            ModifiersBuilders.Clickable.Builder()
                .setId(UUID.randomUUID().toString())
                .setOnClick(
                    ActionBuilders.LaunchAction.Builder()
                        .setAndroidActivity(
                            ActionBuilders.AndroidActivity.Builder()
                                .setClassName(MainActivity::class.java.name)
                                .setPackageName(context.packageName)
                                .build()
                        ).build()
                ).build(),
            DeviceParametersBuilders.DeviceParameters.Builder().build()
        ).setWidth(DimensionBuilders.expand()).setChipColors(
            ChipColors(
                theme.materialColor.primaryVariant.colorProp,
                theme.materialColor.onPrimary.colorProp,
                theme.materialColor.onPrimary.colorProp,
                theme.materialColor.primary.colorProp,
            )
        ).build()
    }

    @Composable
    override fun Content() {
        val eventTextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        val locationTextStyle = TextStyle(
            color = ColorProvider(Color.Gray),
            fontSize = 15.sp
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (LocalTimeInterval.current) {
                timelineMode.timeIntervals.elementAt(0) -> {
                    Text(text = "No event", style = eventTextStyle)
                }

                timelineMode.timeIntervals.elementAt(1) -> {
                    Text(text = "Coffee", style = eventTextStyle)
                    Spacer(GlanceModifier.height(5.dp))
                    Text(text = "Micro Kitchen", style = locationTextStyle)
                }

                timelineMode.timeIntervals.elementAt(2) -> {
                    Text(text = "Work", style = eventTextStyle)
                    Spacer(GlanceModifier.height(5.dp))
                    Text(text = "Remote from home", style = locationTextStyle)
                }
            }

            Spacer(GlanceModifier.height(15.dp))
            Chip(text = "Text", theme = AppTheme.DefaultDarkTheme)
            Image(
                provider = ImageProvider(R.drawable.esmptelegram),
                modifier = GlanceModifier
                    .size(24.dp)
                    .clickable(actionStartActivity(MainActivity::class.java)),
                contentScale = ContentScale.Fit,
                contentDescription = "launch calendar activity"

            )
        }
    }
}
