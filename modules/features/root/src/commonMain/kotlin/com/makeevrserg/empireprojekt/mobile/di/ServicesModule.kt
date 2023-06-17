package com.makeevrserg.empireprojekt.mobile.di

import com.makeevrserg.empireprojekt.mobile.di.impl.root.ServicesModuleImpl
import com.makeevrserg.mobilex.di.Module
import com.makeevrserg.mobilex.di.Single
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

interface ServicesModule : Module {
    val jsonConfiguration: Single<Json>
    val httpClient: Single<HttpClient>

    companion object : ServicesModule by ServicesModuleImpl
}
