package com.makeevrserg.empireprojekt.mobile.api.empireapi

import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.response.GenericPagedModel

interface RatingApi {
    /**
     * Fetch all users
     */
    suspend fun users(page: Int, size: Int, body: RatingsFilterModel): GenericPagedModel<RatingUserModel>

    /**
     * Fetch selected user
     */
    suspend fun user(id: Long): RatingUserModel

    /**
     * Fetch ratings of selected user
     */
    suspend fun ratings(page: Int, size: Int, userId: Long): GenericPagedModel<RatingModel>
}
