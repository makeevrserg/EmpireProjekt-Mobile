package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.status

import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.di.getValue
import io.ktor.client.HttpClient

class StatusModuleImpl : StatusModule {
    private val rootModule by RootModule
    private val servicesModule by ServicesModule

    override val dispatchers: KotlinDispatchers by rootModule.dispatchers
    override val httpClient: HttpClient by servicesModule.httpClient
}
