package com.makeevrserg.empireprojekt.mobile.features.rating.users.data.paging

import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.paging.DefaultPagingCollector
import ru.astrainteractive.klibs.paging.PagingCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

internal class RatingsPagingCollector<T>(
    private val initialPage: Int = 0,
    private val pageSize: Int = 10,
    private val pager: PagedListDataSource<T, RatingsPageContext>,
    private val initialFilter: RatingsFilterModel
) : PagingCollector<T, RatingsPageContext> by DefaultPagingCollector(
    initialPagingState = PagingState(
        pageContext = RatingsPageContext(page = initialPage, filter = initialFilter),
        pageSizeAtLeast = pageSize,
        isLastPage = false,
        isLoading = false,
        isFailure = false,
        items = emptyList()
    ),
    pager = pager,
    pageContextFactory = RatingsPageContext.Factory
)
