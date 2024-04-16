package com.makeevrserg.empireprojekt.mobile.features.rating.user.data

import ru.astrainteractive.empireapi.models.rating.RatingModel

internal interface RatingUserRepository {
    suspend fun getRatings(userId: Long, page: Int, size: Int): List<RatingModel>
}
