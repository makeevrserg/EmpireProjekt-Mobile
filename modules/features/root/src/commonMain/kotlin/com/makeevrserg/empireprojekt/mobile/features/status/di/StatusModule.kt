package com.makeevrserg.empireprojekt.mobile.features.status.di

import com.makeevrserg.mobilex.core.dispatchers.KotlinDispatchers
import com.makeevrserg.mobilex.di.Module
import io.ktor.client.HttpClient

interface StatusModule : Module {
    val dispatchers: KotlinDispatchers
    val httpClient: HttpClient
}
