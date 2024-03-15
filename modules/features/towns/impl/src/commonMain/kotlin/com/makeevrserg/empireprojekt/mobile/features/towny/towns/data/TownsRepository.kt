package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging.TownyPagingCollector
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel

internal interface TownsRepository {
    val pagingCollector: TownyPagingCollector<TownModel>
    suspend fun updateFilter(buildFilter: (TownsFilterModel) -> TownsFilterModel)
}
