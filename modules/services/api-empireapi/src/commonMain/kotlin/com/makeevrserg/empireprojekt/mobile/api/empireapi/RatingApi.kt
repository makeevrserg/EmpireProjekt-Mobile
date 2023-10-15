package com.makeevrserg.empireprojekt.mobile.api.empireapi

import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.empireapi.models.response.GenericPagedModel

interface RatingApi {
    /**
     * Fetch all users
     */
    suspend fun users(body: RatingListRequest): GenericPagedModel<RatingUserModel>

    /**
     * Fetch selected user
     */
    suspend fun user(id: Long): RatingUserModel

    /**
     * Fetch ratings of selected user
     */
    suspend fun ratings(body: UserRatingsRequest): GenericPagedModel<RatingModel>
}
