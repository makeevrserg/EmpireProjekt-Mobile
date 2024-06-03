package com.makeevrserg.empireprojekt.mobile.features.status.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.root.presentation.DefaultRootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.presentation.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

interface StatusModule {

    val rootStatusComponent: RootStatusComponent

    class Default(
        private val coreModule: CoreModule
    ) : StatusModule {

        override val rootStatusComponent: RootStatusComponent by lazy {
            val dependencies = RootStatusComponentDependencies.Default(
                dispatchers = coreModule.dispatchers,
                storeFactory = DefaultStoreFactory(),
                httpClient = coreModule.httpClient
            )
            DefaultRootStatusComponent(
                dependencies = dependencies
            )
        }
    }
}
