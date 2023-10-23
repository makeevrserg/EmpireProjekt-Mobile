package com.makeevrserg.empireprojekt.mobile.api.empireapi.di

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import com.makeevrserg.empireprojekt.mobile.api.empireapi.impl.RatingApiImpl
import io.ktor.client.HttpClient
import ru.astrainteractive.klibs.kdi.Provider
import ru.astrainteractive.klibs.kdi.getValue

interface EmpireApiModule {
    val ratingApi: RatingApi

    class Default(httpClient: HttpClient) : EmpireApiModule {
        override val ratingApi: RatingApi by Provider {
            RatingApiImpl(httpClient = httpClient)
        }
    }
}
