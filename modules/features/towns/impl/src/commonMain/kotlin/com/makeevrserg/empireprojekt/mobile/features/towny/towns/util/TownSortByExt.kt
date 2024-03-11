package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import ru.astrainteractive.empireapi.models.towny.TownSortBy

object TownSortByExt {
    fun TownSortBy.toStringDesc() = when (this) {
        TownSortBy.NONE -> MR.strings.towns_town_sort_by_none
        TownSortBy.NAME_ASC -> MR.strings.towns_town_sort_by_name_asc
        TownSortBy.NAME_DESC -> MR.strings.towns_town_sort_by_name_desc
        TownSortBy.TAG_ASC -> MR.strings.towns_town_sort_by_tag_asc
        TownSortBy.TAG_DESC -> MR.strings.towns_town_sort_by_tag_desc
        TownSortBy.FOUNDER_ASC -> MR.strings.towns_town_sort_by_founder_asc
        TownSortBy.FOUNDER_DESC -> MR.strings.towns_town_sort_by_founder_desc
        TownSortBy.NATION_ASC -> MR.strings.towns_town_sort_by_nation_asc
        TownSortBy.NATION_DESC -> MR.strings.towns_town_sort_by_nation_desc
        TownSortBy.DATE_ASC -> MR.strings.towns_town_sort_by_date_asc
        TownSortBy.DATE_DESC -> MR.strings.towns_town_sort_by_date_desc
        TownSortBy.RESIDENTS_ASC -> MR.strings.towns_town_sort_by_residents_asc
        TownSortBy.RESIDENTS_DESC -> MR.strings.towns_town_sort_by_residents_desc
    }
}
