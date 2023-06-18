package com.makeevrserg.empireprojekt.mobile.features.root.di.factories

import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Factory

expect class LinkBrowserFactory(platformConfiguration: PlatformConfiguration) : Factory<LinkBrowser>
