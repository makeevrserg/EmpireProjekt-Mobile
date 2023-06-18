package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface RootComponent : BackHandlerOwner {
    val childStack: Value<ChildStack<*, DefaultRootComponent.Configuration>>
    val childSlot: Value<ChildSlot<*, DefaultRootComponent.SlotConfiguration>>

    fun dismissSlotChild()
    fun pushSlot(slot: SlotChild)

    fun push(screen: Child)
    fun replaceCurrent(screen: Child)
    fun replaceAll(screen: Child)
    fun pop()

    sealed interface Child : Parcelable {
        @Parcelize
        object Splash : Child

        @Parcelize
        object Status : Child
    }
    sealed interface SlotChild : Parcelable {
        @Parcelize
        object Settings : SlotChild
    }
}
