package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import ru.astrainteractive.empireapi.models.towny.TownPublicType

object TownPublicTypeExt {
    fun TownPublicType.toStringDesc() = when (this) {
        TownPublicType.NONE -> TR.strings.towns_town_sort_by_none
        TownPublicType.PUBLIC -> TR.strings.towns_town_public_type_public
        TownPublicType.PRIVATE -> TR.strings.towns_town_public_type_private
    }
}
