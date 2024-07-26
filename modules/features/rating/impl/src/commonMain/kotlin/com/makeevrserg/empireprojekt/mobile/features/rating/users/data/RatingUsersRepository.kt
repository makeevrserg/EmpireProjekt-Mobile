package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.response.GenericPagedModel

internal interface RatingUsersRepository {
    suspend fun fetchUsers(page: Int, filterModel: RatingsFilterModel): Result<GenericPagedModel<RatingUserModel>>
}
