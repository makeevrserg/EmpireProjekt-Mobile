package com.makeevrserg.empireprojekt.mobile.features.root.di.impl.status

import com.makeevrserg.empireprojekt.mobile.features.root.di.RootModule
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.kdi.getValue
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

class StatusModuleImpl(rootModule: RootModule) : StatusModule {
    private val servicesModule by rootModule.servicesModule

    override val dispatchers: KotlinDispatchers by rootModule.dispatchers
    override val httpClient: HttpClient by servicesModule.httpClient
}
