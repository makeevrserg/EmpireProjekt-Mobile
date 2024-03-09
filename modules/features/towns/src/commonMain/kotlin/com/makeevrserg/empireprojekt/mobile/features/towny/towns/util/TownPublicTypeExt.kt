package com.makeevrserg.empireprojekt.mobile.features.towny.towns.util

import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import ru.astrainteractive.empireapi.models.towny.TownPublicType

object TownPublicTypeExt {
    fun TownPublicType.toStringDesc() = when (this) {
        TownPublicType.NONE -> StringDesc.Raw("None")
        TownPublicType.PUBLIC -> StringDesc.Raw("Public")
        TownPublicType.PRIVATE -> StringDesc.Raw("Private")
    }
}
