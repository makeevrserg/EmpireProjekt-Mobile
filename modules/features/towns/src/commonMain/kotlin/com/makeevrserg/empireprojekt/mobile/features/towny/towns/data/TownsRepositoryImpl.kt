package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data

import com.makeevrserg.empireprojekt.mobile.api.empireapi.TownyApi
import kotlinx.coroutines.withContext
import ru.astrainteractive.empireapi.models.towny.TownModel
import ru.astrainteractive.klibs.mikro.core.dispatchers.KotlinDispatchers
import ru.astrainteractive.klibs.paging.IntPagerCollector
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource

internal class TownsRepositoryImpl(
    private val townyApi: TownyApi,
    private val dispatchers: KotlinDispatchers
) : TownsRepository {

    private suspend fun loadPage(page: Int): Result<List<TownModel>> {
        return runCatching {
            withContext(dispatchers.IO) {
                townyApi.towns(
                    page = page,
                    size = 10,
                ).data
            }
        }.onFailure(Throwable::printStackTrace)
    }

    override val pagingCollector = IntPagerCollector(
        initialPage = 0,
        pager = LambdaPagedListDataSource {
            loadPage(it.pageContext.page)
        }
    )
}
