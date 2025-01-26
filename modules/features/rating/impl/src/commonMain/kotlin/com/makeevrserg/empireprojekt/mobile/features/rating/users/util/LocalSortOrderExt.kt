package com.makeevrserg.empireprojekt.mobile.features.rating.users.util

import com.makeevrserg.empireprojekt.mobile.rating.RR
import ru.astrainteractive.empireapi.models.towny.LocalSortOrder

object LocalSortOrderExt {
    fun LocalSortOrder.toStringDesc() = when (this) {
        LocalSortOrder.NONE -> RR.strings.rating_town_sort_by_none
        LocalSortOrder.ASC -> RR.strings.rating_local_sort_asc
        LocalSortOrder.DESC -> RR.strings.rating_local_sort_desc
    }
}
