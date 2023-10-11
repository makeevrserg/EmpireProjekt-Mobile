package com.makeevrserg.empireprojekt.mobile.features.root.di.factory

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import ru.astrainteractive.klibs.kdi.Factory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

expect class LinkBrowserFactory(platformConfiguration: PlatformConfiguration) : Factory<LinkBrowser>
