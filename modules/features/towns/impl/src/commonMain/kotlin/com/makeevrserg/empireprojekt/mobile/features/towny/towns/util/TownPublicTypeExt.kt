package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import ru.astrainteractive.empireapi.models.towny.TownPublicType

object TownPublicTypeExt {
    fun TownPublicType.toStringDesc() = when (this) {
        TownPublicType.NONE -> MR.strings.towns_town_public_type_none
        TownPublicType.PUBLIC -> MR.strings.towns_town_public_type_public
        TownPublicType.PRIVATE -> MR.strings.towns_town_public_type_private
    }
}
