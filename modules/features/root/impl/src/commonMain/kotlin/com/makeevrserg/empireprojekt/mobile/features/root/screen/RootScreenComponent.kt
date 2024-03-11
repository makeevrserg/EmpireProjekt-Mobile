package com.makeevrserg.empireprojekt.mobile.features.root.screen

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.makeevrserg.empireprojekt.mobile.services.core.PopComponent

interface RootScreenComponent : BackHandlerOwner, PopComponent {
    val childStack: Value<ChildStack<*, DefaultRootScreenComponent.Configuration>>

    fun push(screen: Child)
    fun replaceCurrent(screen: Child)
    fun replaceAll(screen: Child)

    sealed interface Child : Parcelable {
        @Parcelize
        data object Splash : Child

        @Parcelize
        data object Pager : Child

        @Parcelize
        class RatingUser(val userId: Long, val userName: String) : Child
    }
}
