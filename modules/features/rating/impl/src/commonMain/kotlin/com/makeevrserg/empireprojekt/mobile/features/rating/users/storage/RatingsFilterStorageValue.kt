package com.makeevrserg.empireprojekt.mobile.features.rating.users.storage

import com.makeevrserg.empireprojekt.mobile.services.core.settings.SettingsExt.getEnumByOrdinalOrDefault
import com.makeevrserg.empireprojekt.mobile.services.core.settings.SettingsExt.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.klibs.kstorage.api.flow.StateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultStateFlowMutableKrate

private class Keys(key: String) {
    val queryKey: String = "${key}queryKey"
    val nameSortKey: String = "${key}nameSortKey"
    val lastUpdatedSortKey: String = "${key}lastUpdatedSortKey"
    val ratingSortKey: String = "${key}ratingSortKey"
}

internal class RatingsFilterStorageValue(
    settings: Settings,
    key: String,
    default: RatingsFilterModel = RatingsFilterModel()
) : StateFlowMutableKrate<RatingsFilterModel> by DefaultStateFlowMutableKrate(
    factory = { default },
    loader = {
        val keys = Keys(key)
        RatingsFilterModel(
            query = settings.getString(keys.queryKey, default.query),
            nameSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.nameSortKey,
                LocalSortOrder.NONE
            ),
            lastUpdatedSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.lastUpdatedSortKey,
                LocalSortOrder.NONE
            ),
            ratingSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.ratingSortKey,
                LocalSortOrder.NONE
            ),
        )
    },
    saver = {
        val keys = Keys(key)
        settings.putString(keys.queryKey, it.query)
        settings.putEnumByOrdinal(keys.nameSortKey, it.nameSort)
        settings.putEnumByOrdinal(keys.lastUpdatedSortKey, it.lastUpdatedSort)
        settings.putEnumByOrdinal(keys.ratingSortKey, it.ratingSort)
    }
)
