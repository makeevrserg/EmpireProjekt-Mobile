package com.makeevrserg.empireprojekt.mobile.features.status.root

import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.url.DefaultUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.url.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature

class DefaultRootStatusComponent(
    private val statusModule: StatusModule
) : RootStatusComponent {
    override val statusComponents: List<UrlStatusComponent> = buildList {
        DefaultUrlStatusComponent(
            title = "empireprojekt.ru",
            coroutineFeature = CoroutineFeature.Default(),
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "https://empireprojekt.ru"
            ).create()
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "astrainteractive.ru",
            coroutineFeature = CoroutineFeature.Default(),
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "https://astrainteractive.ru"
            ).create()
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Dev: AstraLearner",
            coroutineFeature = CoroutineFeature.Default(),
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "http://astralearner.empireprojekt.ru:8083/dictionaries/4/words"
            ).create()
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Prod: AstraLearner",
            coroutineFeature = CoroutineFeature.Default(),
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "http://astralearner.empireprojekt.ru:8081/dictionaries/4/words"
            ).create()
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Empire SMP",
            coroutineFeature = CoroutineFeature.Default(),
            urlStatusRepository = statusModule.minecraftStatusRepository
        ).run(::add)
    }
}
