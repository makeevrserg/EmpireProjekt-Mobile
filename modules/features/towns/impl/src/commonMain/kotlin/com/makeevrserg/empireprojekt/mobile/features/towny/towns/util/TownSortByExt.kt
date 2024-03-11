package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import ru.astrainteractive.empireapi.models.towny.TownSortBy

object TownSortByExt {
    fun TownSortBy.toStringDesc() = when (this) {
        TownSortBy.NONE -> StringDesc.Raw("None")
        TownSortBy.NAME_ASC -> StringDesc.Raw("Name (asc)")
        TownSortBy.NAME_DESC -> StringDesc.Raw("Name (desc)")
        TownSortBy.TAG_ASC -> StringDesc.Raw("Tag (asc)")
        TownSortBy.TAG_DESC -> StringDesc.Raw("Tag (desc)")
        TownSortBy.FOUNDER_ASC -> StringDesc.Raw("Founder (asc)")
        TownSortBy.FOUNDER_DESC -> StringDesc.Raw("Founder (desc)")
        TownSortBy.NATION_ASC -> StringDesc.Raw("Nation (asc)")
        TownSortBy.NATION_DESC -> StringDesc.Raw("Nation (desc)")
        TownSortBy.DATE_ASC -> StringDesc.Raw("Date (asc)")
        TownSortBy.DATE_DESC -> StringDesc.Raw("Date (desc)")
        TownSortBy.RESIDENTS_ASC -> StringDesc.Raw("Residents (asc)")
        TownSortBy.RESIDENTS_DESC -> StringDesc.Raw("Residents (desc)")
    }
}
