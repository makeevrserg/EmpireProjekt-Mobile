package com.makeevrserg.empireprojekt.mobile.features.status.root

import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.mincraft.DefaultMinecraftStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.url.DefaultUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature

class DefaultRootStatusComponent(
    private val statusModule: StatusModule
) : RootStatusComponent {
    override val statusComponents: List<StatusComponent> = buildList {

        DefaultUrlStatusComponent(
            url = "https://empireprojekt.ru",
            title = "empireprojekt.ru",
            module = statusModule,
            coroutineFeature = CoroutineFeature.Default()
        ).run(::add)

        DefaultUrlStatusComponent(
            url = "https://astrainteractive.ru",
            title = "astrainteractive.ru",
            module = statusModule,
            coroutineFeature = CoroutineFeature.Default()
        ).run(::add)

        DefaultUrlStatusComponent(
            url = "http://astralearner.empireprojekt.ru:8083/dictionaries/4/words",
            title = "Dev: AstraLearner",
            module = statusModule,
            coroutineFeature = CoroutineFeature.Default()
        ).run(::add)

        DefaultUrlStatusComponent(
            url = "http://astralearner.empireprojekt.ru:8081/dictionaries/4/words",
            title = "Prod: AstraLearner",
            module = statusModule,
            coroutineFeature = CoroutineFeature.Default()
        ).run(::add)

        DefaultMinecraftStatusComponent(
            title = "Empire SMP",
            module = statusModule,
            coroutineFeature = CoroutineFeature.Default()
        ).run(::add)
    }
}
