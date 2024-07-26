package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.makeevrserg.empireprojekt.mobile.features.status.root.presentation.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent
import kotlinx.coroutines.flow.StateFlow

interface PagerComponent {
    val selectedBottomBarItem: StateFlow<PagerBottomBarItem>
    val selectedChild: StateFlow<Child>

    fun select(item: PagerBottomBarItem)

    fun selectStatus()

    fun selectRatings()

    fun selectTowns()

    fun selectMap()

    sealed interface Child {
        class Status(
            val rootStatusComponent: RootStatusComponent,
            val themeSwitcherComponent: ThemeSwitcherComponent
        ) : Child

        class RatingUsers(
            val ratingUsersComponent: RatingUsersComponent
        ) : Child

        class Towns(
            val townsComponent: TownsComponent
        ) : Child

        data object Map : Child
    }
}
