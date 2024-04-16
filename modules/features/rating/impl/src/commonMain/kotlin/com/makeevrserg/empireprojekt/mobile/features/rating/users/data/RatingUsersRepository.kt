package com.makeevrserg.empireprojekt.mobile.features.rating.users.data

import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.paging.RatingsPagingCollector
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel

internal interface RatingUsersRepository {
    val pagingCollector: RatingsPagingCollector<RatingUserModel>
    suspend fun updateFilter(buildFilter: (RatingsFilterModel) -> RatingsFilterModel)
}
