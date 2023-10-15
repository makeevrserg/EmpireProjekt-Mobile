package ru.astrainteractive.empireapi.models.response

interface PagedModel<T> {
    val data: List<T>
    val total: Long
    val currentPageAmount: Int
    val page: Int
}
