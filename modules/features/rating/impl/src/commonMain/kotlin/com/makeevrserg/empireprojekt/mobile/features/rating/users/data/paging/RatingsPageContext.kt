package com.makeevrserg.empireprojekt.mobile.features.rating.users.data.paging

import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.klibs.paging.context.PageContext

internal data class RatingsPageContext(
    val page: Int = 0,
    val filter: RatingsFilterModel
) : PageContext {
    object Factory : PageContext.Factory<RatingsPageContext> {
        override fun next(pageContext: RatingsPageContext): RatingsPageContext {
            return pageContext.copy(page = pageContext.page + 1)
        }
    }
}
