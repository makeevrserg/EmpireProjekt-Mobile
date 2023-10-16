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
        object Splash : Child

        @Parcelize
        object Status : Child

        @Parcelize
        object RatingUsers : Child

        @Parcelize
        class RatingUser(val userId: Long) : Child
    }
}
