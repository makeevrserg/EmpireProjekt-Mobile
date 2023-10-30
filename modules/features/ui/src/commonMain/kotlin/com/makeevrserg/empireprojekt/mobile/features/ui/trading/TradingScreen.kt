package com.makeevrserg.empireprojekt.mobile.features.ui.trading

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.components.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.components.topbar.AstraCenterAlignedTopAppBar
import com.tradingview.lightweightcharts.api.chart.models.color.surface.SolidColor
import com.tradingview.lightweightcharts.api.chart.models.color.surface.SurfaceColor
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi
import com.tradingview.lightweightcharts.api.options.models.GridLineOptions
import com.tradingview.lightweightcharts.api.options.models.GridOptions
import com.tradingview.lightweightcharts.api.options.models.PriceScaleOptions
import com.tradingview.lightweightcharts.api.options.models.TimeScaleOptions
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.series.common.SeriesData
import com.tradingview.lightweightcharts.api.series.enums.PriceScaleMode
import com.tradingview.lightweightcharts.api.series.models.HistogramData
import com.tradingview.lightweightcharts.api.series.models.LineData
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.api.series.models.WhitespaceData
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import ru.astrainteractive.klibs.kdi.Factory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TradingScreen(tradingComponent: TradingComponent) {
    val model by tradingComponent.model.collectAsState()
    var histogramSeries: SeriesApi? by remember {
        mutableStateOf(null)
    }
    histogramSeries?.let {
        val data = model.entries.map {
            LineData(
                time = Time.Utc(it.instant.epochSeconds),
                value = it.value
            )
        }
        it.setData(data)
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            AstraCenterAlignedTopAppBar(title = "Trading")
        }
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .navBarsPadding()
                .background(androidx.compose.ui.graphics.Color.Red),
            factory = { context ->
                ChartsView(
                    context = context
                ).also { chartsView ->
                    chartsView.api.applyOptions {
                        val priceScaleOptions = PriceScaleOptions(
                            autoScale = true,
                            visible = true,
                            ticksVisible = true,
                            borderVisible = true
                        )
                        leftPriceScale = priceScaleOptions
                        rightPriceScale = priceScaleOptions
                        overlayPriceScale = priceScaleOptions

                        timeScale = TimeScaleOptions(
                            timeVisible = true,
                            secondsVisible = true
                        )
                        layout = layoutOptions {
                            background = SolidColor(Color.TRANSPARENT.toIntColor())
                            textColor = Color.BLACK.toIntColor()
                        }
                        localization = localizationOptions {
                            locale = "ru-RU"
                            priceFormatter = PriceFormatter(template = "{price:#2:#2}$")
                            timeFormatter = TimeFormatter(
                                locale = "ru-RU",
                                dateTimeFormat = DateTimeFormat.DATE_TIME
                            )
                        }
                    }
                    chartsView.api.addLineSeries(
                        onSeriesCreated = {
                            histogramSeries = it
                        }
                    )
                }
            }
        )
    }
}
