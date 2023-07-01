package com.makeevrserg.empireprojekt.mobile.features.root.di.factories

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Factory

@Suppress("UnusedPrivateMember")
actual class LinkBrowserFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) : Factory<LinkBrowser> {
    override fun create(): LinkBrowser {
        return object : LinkBrowser {
            override fun openInBrowser(url: String) {
                TODO("Not yet implemented")
            }
        }
    }
}
