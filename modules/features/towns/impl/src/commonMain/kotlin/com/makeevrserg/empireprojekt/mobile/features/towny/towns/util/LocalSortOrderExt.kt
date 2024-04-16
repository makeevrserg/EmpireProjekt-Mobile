package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> MR.strings.towns_town_sort_by_none
        LocalSortOrder.ASC -> MR.strings.towns_local_sort_asc
        LocalSortOrder.DESC -> MR.strings.towns_local_sort_desc
    }
}
