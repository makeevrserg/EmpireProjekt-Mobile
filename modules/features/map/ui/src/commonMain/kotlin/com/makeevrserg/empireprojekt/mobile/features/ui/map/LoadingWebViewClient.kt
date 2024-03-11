package com.makeevrserg.empireprojekt.mobile.features.ui.map

import android.webkit.WebView
import android.webkit.WebViewClient

internal class LoadingWebViewClient(
    private val onLoading: (Boolean) -> Unit
) : WebViewClient() {

    init {
        onLoading.invoke(true)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        onLoading.invoke(true)
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageFinished(view: WebView, url: String) {
        onLoading.invoke(false)
        return super.onPageFinished(view, url)
    }
}
