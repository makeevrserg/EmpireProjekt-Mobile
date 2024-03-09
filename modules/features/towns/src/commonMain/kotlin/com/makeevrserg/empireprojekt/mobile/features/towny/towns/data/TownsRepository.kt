package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.klibs.paging.IntPagerCollector

internal interface TownsRepository {
    val pagingCollector: IntPagerCollector<TownModel>
}
