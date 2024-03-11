package com.makeevrserg.empireprojekt.mobile.features.towny.towns.storage

import com.russhwolf.settings.Settings
import ru.astrainteractive.empireapi.models.towny.TownPublicType
import ru.astrainteractive.empireapi.models.towny.TownSortBy
import ru.astrainteractive.empireapi.models.towny.TownsFilter
import ru.astrainteractive.klibs.kstorage.MutableStorageValue
import ru.astrainteractive.klibs.kstorage.api.MutableStorageValue

private val String.queryKey: String
    get() = "${this}_query_key"

private val String.publicTypeKey: String
    get() = "${this}_public_type_key"

private val String.sortByTypeKey: String
    get() = "${this}_sort_type_key"

internal class TownsFilterStorageValue(
    settings: Settings,
    key: String,
    default: TownsFilter = TownsFilter()
) : MutableStorageValue<TownsFilter> by MutableStorageValue(
    default = default,
    loadSettingsValue = {
        TownsFilter(
            query = settings.getString(key.queryKey, default.query),
            publicType = TownPublicType.entries[settings.getInt(key.publicTypeKey, 0)],
            sortBy = TownSortBy.entries[settings.getInt(key.sortByTypeKey, 0)],
        )
    },
    saveSettingsValue = {
        settings.putString(key.queryKey, it.query)
        settings.putInt(key.publicTypeKey, it.publicType.ordinal)
        settings.putInt(key.sortByTypeKey, it.sortBy.ordinal)
    }
)
