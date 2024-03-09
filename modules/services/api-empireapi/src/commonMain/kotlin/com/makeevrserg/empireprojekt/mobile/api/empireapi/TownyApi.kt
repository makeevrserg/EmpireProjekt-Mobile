package com.makeevrserg.empireprojekt.mobile.api.empireapi

import ru.astrainteractive.empireapi.models.response.GenericPagedModel
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.empireapi.models.towny.TownsFilter

interface TownyApi {
    /**
     * Fetch all towns
     */
    suspend fun towns(page: Int, size: Int, filter: TownsFilter): GenericPagedModel<TownModel>
}
