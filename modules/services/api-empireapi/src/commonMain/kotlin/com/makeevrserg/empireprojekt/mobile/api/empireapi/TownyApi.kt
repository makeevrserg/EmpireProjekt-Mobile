package com.makeevrserg.empireprojekt.mobile.api.empireapi

import ru.astrainteractive.empireapi.models.response.GenericPagedModel
import ru.astrainteractive.empireapi.models.towny.TownModel

interface TownyApi {
    /**
     * Fetch all towns
     */
    suspend fun towns(page: Int, size: Int): GenericPagedModel<TownModel>
}
