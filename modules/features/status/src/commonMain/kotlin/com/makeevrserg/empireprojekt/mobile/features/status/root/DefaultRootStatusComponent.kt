package com.makeevrserg.empireprojekt.mobile.features.status.root

import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.url.DefaultUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.url.UrlStatusComponent

class DefaultRootStatusComponent(
    private val statusModule: StatusModule
) : RootStatusComponent {
    override val statusComponents: List<UrlStatusComponent> = buildList {
        DefaultUrlStatusComponent(
            title = "empireprojekt.ru",
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "https://empireprojekt.ru"
            ).create(),
            storeFactory = statusModule.storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "astrainteractive.ru",
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "https://astrainteractive.ru"
            ).create(),
            storeFactory = statusModule.storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Dev: AstraLearner",
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "http://astralearner.empireprojekt.ru:8083/dictionaries/4/words"
            ).create(),
            storeFactory = statusModule.storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Prod: AstraLearner",
            urlStatusRepository = statusModule.urlStatRepositoryFactory(
                "http://astralearner.empireprojekt.ru:8081/dictionaries/4/words"
            ).create(),
            storeFactory = statusModule.storeFactory,
        ).run(::add)

        DefaultUrlStatusComponent(
            title = "Empire SMP",
            urlStatusRepository = statusModule.minecraftStatusRepository,
            storeFactory = statusModule.storeFactory,
        ).run(::add)
    }
}
