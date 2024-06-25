package com.makeevrserg.empireprojekt.mobile.features.towny.towns.storage

import com.makeevrserg.empireprojekt.mobile.services.core.settings.SettingsExt.getEnumByOrdinalOrDefault
import com.makeevrserg.empireprojekt.mobile.services.core.settings.SettingsExt.putEnumByOrdinal
import com.russhwolf.settings.Settings
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.kstorage.api.Krate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultStateFlowMutableKrate

private class Keys(key: String) {
    val queryKey: String = "${key}queryKey"
    val publicTypeKey: String = "${key}publicTypeKey"
    val nameSortKey: String = "${key}nameSortKey"
    val tagSortKey: String = "${key}tagSortKey"
    val founderSortKey: String = "${key}founderSortKey"
    val nationSortKey: String = "${key}nationSortKey"
    val dateSortKey: String = "${key}dateSortKey"
    val residentsSortKey: String = "${key}residentsSortKey"
}

internal class TownsFilterStorageValue(
    settings: Settings,
    key: String,
    default: TownsFilterModel = TownsFilterModel()
) : Krate.Mutable<TownsFilterModel> by DefaultStateFlowMutableKrate(
    factory = { default },
    loader = {
        val keys = Keys(key)
        TownsFilterModel(
            query = settings.getString(keys.queryKey, default.query),
            publicType = settings.getEnumByOrdinalOrDefault(
                TownPublicType.entries,
                keys.publicTypeKey,
                TownPublicType.NONE
            ),
            nameSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.nameSortKey,
                LocalSortOrder.NONE
            ),
            tagSort = settings.getEnumByOrdinalOrDefault(LocalSortOrder.entries, keys.tagSortKey, LocalSortOrder.NONE),
            founderSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.founderSortKey,
                LocalSortOrder.NONE
            ),
            nationSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.nationSortKey,
                LocalSortOrder.NONE
            ),
            dateSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.dateSortKey,
                LocalSortOrder.NONE
            ),
            residentsSort = settings.getEnumByOrdinalOrDefault(
                LocalSortOrder.entries,
                keys.residentsSortKey,
                LocalSortOrder.NONE
            ),
        )
    },
    saver = {
        val keys = Keys(key)
        settings.putString(keys.queryKey, it.query)
        settings.putEnumByOrdinal(keys.publicTypeKey, it.publicType)
        settings.putEnumByOrdinal(keys.nameSortKey, it.nameSort)
        settings.putEnumByOrdinal(keys.tagSortKey, it.tagSort)
        settings.putEnumByOrdinal(keys.founderSortKey, it.founderSort)
        settings.putEnumByOrdinal(keys.nationSortKey, it.nationSort)
        settings.putEnumByOrdinal(keys.dateSortKey, it.dateSort)
        settings.putEnumByOrdinal(keys.residentsSortKey, it.residentsSort)
    }
)
