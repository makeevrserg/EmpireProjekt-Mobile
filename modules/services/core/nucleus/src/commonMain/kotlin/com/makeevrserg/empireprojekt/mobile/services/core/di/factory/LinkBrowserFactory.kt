package com.makeevrserg.empireprojekt.mobile.services.core.di.factory

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

internal expect class LinkBrowserFactory(platformConfiguration: PlatformConfiguration) : Factory<LinkBrowser>
