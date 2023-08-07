package com.makeevrserg.empireprojekt.mobile.features.root.di.factories

import com.makeevrserg.empireprojekt.mobile.services.core.AndroidLinkBrowser
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

actual class LinkBrowserFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) : Factory<LinkBrowser> {
    override fun create(): LinkBrowser {
        return AndroidLinkBrowser(platformConfiguration.applicationContext)
    }
}
