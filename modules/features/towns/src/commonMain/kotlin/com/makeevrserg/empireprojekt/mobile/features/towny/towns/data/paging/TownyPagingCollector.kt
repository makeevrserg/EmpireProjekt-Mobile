package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging

import ru.astrainteractive.empireapi.models.towny.TownsFilter
import ru.astrainteractive.klibs.paging.DefaultPagingCollector
import ru.astrainteractive.klibs.paging.PagingCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

internal class TownyPagingCollector<T>(
    private val initialPage: Int = 0,
    private val pageSize: Int = 10,
    private val pager: PagedListDataSource<T, TownyPageContext>,
    private val initialFilter: TownsFilter = TownsFilter()
) : PagingCollector<T, TownyPageContext> by DefaultPagingCollector(
    initialPagingState = PagingState(
        pageContext = TownyPageContext(page = initialPage, filter = initialFilter),
        pageSizeAtLeast = pageSize,
        isLastPage = false,
        isLoading = false,
        isFailure = false,
        items = emptyList()
    ),
    pager = pager,
    pageContextFactory = TownyPageContext.Factory
)
