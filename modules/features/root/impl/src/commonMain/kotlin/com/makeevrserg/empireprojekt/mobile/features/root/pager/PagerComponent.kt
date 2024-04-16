package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.rating.users.presentation.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.presentation.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent

interface PagerComponent {
    val selectedIndex: Value<Int>
    val selectedChild: Value<Child>

//    fun selectStatus()

//    fun selectRatings()

//    fun selectTowns()

//    fun selectMap()

    fun select(index: Int)

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
