package ru.astrainteractive.empireapi.models.response

import kotlinx.serialization.Serializable

@Serializable
class GenericPagedModel<T>(
    override val data: List<T>,
    override val total: Long,
    override val currentPageAmount: Int,
    override val page: Int
) : PagedModel<T>
