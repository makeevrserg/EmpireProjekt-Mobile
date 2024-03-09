package com.makeevrserg.empireprojekt.mobile.features.root.pager

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.rating.users.RatingUsersComponent
import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent

interface PagerComponent {
    val childStack: Value<ChildStack<*, Child>>
    val selectedIndex: Value<Int>

    fun selectStatus()

    fun selectRatings()

    fun selectTowns()

    fun selectMap()

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
