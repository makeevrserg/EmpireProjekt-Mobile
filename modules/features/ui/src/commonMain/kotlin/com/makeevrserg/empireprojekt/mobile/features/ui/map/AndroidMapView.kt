package com.makeevrserg.empireprojekt.mobile.features.ui.map

import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.components.AstraLoading
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
internal fun AndroidMapView() {
    var webView: WebView? = remember {
        null
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Red),
        factory = { context ->
            webView ?: WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                loadUrl("https://map.astrainteractive.ru")
                settings.javaScriptEnabled = true
                settings.useWideViewPort = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                settings.domStorageEnabled = true
                settings.blockNetworkLoads = false
                settings.blockNetworkImage = false
                settings.databaseEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                settings.setSupportZoom(true)
                scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
                isScrollbarFadingEnabled = false
                webViewClient = LoadingWebViewClient {
                    isLoading = it
                }
            }.also { webView = it }
        },
        update = {
            webView = it
        }
    )
    Crossfade(
        targetState = isLoading,
        label = "loading indicator crossfade"
    ) { isLoading ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AstraLoading(AppTheme.dimens.M)
            }
        }
    }
}
