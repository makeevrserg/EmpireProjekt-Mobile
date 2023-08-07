package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface RootBottomSheetComponent {
    fun dismissSlotChild()
    fun pushSlot(slot: Child)
    val childSlot: Value<ChildSlot<*, DefaultRootBottomSheetComponent.Configuration>>

    sealed interface Child : Parcelable {
        @Parcelize
        object Settings : Child
    }
}
