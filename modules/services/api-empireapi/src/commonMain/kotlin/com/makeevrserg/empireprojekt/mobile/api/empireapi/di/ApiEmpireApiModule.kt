package com.makeevrserg.empireprojekt.mobile.api.empireapi.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.impl.RatingApiImpl
import com.makeevrserg.empireprojekt.mobile.api.empireapi.impl.TownyApiImpl
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface ApiEmpireApiModule {
    val ratingApi: RatingApi
    val townyApi: TownyApi

    class Default(coreModule: CoreModule) : ApiEmpireApiModule {
        private val url = BuildKonfig.PROD_URL

        override val ratingApi: RatingApi by lazy {
            RatingApiImpl(httpClient = coreModule.httpClient, baseUrl = url)
        }

        override val townyApi: TownyApi by lazy {
            TownyApiImpl(httpClient = coreModule.httpClient, baseUrl = url)
        }
    }
}
