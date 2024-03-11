package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging.TownyPagingCollector
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilter

internal interface TownsRepository {
    val pagingCollector: TownyPagingCollector<TownModel>
    suspend fun updateFilter(buildFilter: (TownsFilter) -> TownsFilter)
}
