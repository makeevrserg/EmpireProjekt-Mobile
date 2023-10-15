package ru.astrainteractive.empireapi.models.rating

import ru.astrainteractive.empireapi.models.response.PagedModel

class RatingModelPagedModel(
    override val data: List<RatingModel>,
    override val total: Long,
    override val currentPageAmount: Int,
    override val page: Int
) : PagedModel<RatingModel>
