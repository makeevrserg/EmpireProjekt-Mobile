package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.root.di.impl.root.ServicesModuleImpl
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser
import com.makeevrserg.mobilex.core.platform.PlatformConfiguration
import com.makeevrserg.mobilex.di.Lateinit
import com.makeevrserg.mobilex.di.Module
import com.makeevrserg.mobilex.di.Single
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

interface ServicesModule : Module {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val jsonConfiguration: Single<Json>
    val httpClient: Single<HttpClient>
    val linkBrowser: Single<LinkBrowser>
    companion object : ServicesModule by ServicesModuleImpl
}
