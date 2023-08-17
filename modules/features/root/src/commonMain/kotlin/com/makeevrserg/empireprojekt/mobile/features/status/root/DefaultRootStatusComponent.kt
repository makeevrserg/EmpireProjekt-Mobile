package com.makeevrserg.empireprojekt.mobile.features.status.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.empireprojekt.mobile.features.status.StatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.di.StatusModule
import com.makeevrserg.empireprojekt.mobile.features.status.mincraft.DefaultMinecraftStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.url.DefaultUrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.services.core.CoroutineFeature

class DefaultRootStatusComponent(
    context: ComponentContext,
    private val statusModule: StatusModule
) : RootStatusComponent, ComponentContext by context {
    override val statusComponents: List<StatusComponent> = buildList {

        DefaultUrlStatusComponent(
            context = context,
            url = "https://empireprojekt.ru",
            title = "empireprojekt.ru",
            module = statusModule,
            coroutineFeature = context.instanceKeeper.getOrCreate {
                CoroutineFeature.Default()
            }
        ).run(::add)

        DefaultUrlStatusComponent(
            context = context,
            url = "https://astrainteractive.ru",
            title = "astrainteractive.ru",
            module = statusModule,
            coroutineFeature = context.instanceKeeper.getOrCreate {
                CoroutineFeature.Default()
            }
        ).run(::add)

        DefaultUrlStatusComponent(
            context = context,
            url = "http://astralearner.empireprojekt.ru:8083/dictionaries/4/words",
            title = "Dev: AstraLearner",
            module = statusModule,
            coroutineFeature = context.instanceKeeper.getOrCreate {
                CoroutineFeature.Default()
            }
        ).run(::add)

        DefaultUrlStatusComponent(
            context = context,
            url = "http://astralearner.empireprojekt.ru:8081/dictionaries/4/words",
            title = "Prod: AstraLearner",
            module = statusModule,
            coroutineFeature = context.instanceKeeper.getOrCreate {
                CoroutineFeature.Default()
            }
        ).run(::add)

        DefaultMinecraftStatusComponent(
            context = context,
            title = "Empire SMP",
            module = statusModule,
            coroutineFeature = context.instanceKeeper.getOrCreate {
                CoroutineFeature.Default()
            }
        ).run(::add)
    }
}
