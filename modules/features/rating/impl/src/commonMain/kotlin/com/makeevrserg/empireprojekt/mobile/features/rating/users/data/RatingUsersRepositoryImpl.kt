package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.response.GenericPagedModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers

internal class RatingUsersRepositoryImpl(
    private val ratingApi: RatingApi,
    private val dispatchers: KotlinDispatchers
) : RatingUsersRepository {

    override suspend fun fetchUsers(
        page: Int,
        filterModel: RatingsFilterModel
    ): Result<GenericPagedModel<RatingUserModel>> {
        return withContext(dispatchers.IO) {
            runCatching {
                ratingApi.users(
                    page = page,
                    size = 10,
                    body = filterModel
                )
            }
        }
    }
}
