package com.makeevrserg.empireprojekt.mobile.features.root.modal

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

interface RootBottomSheetComponent : RootBottomSheetRouter {

    val childSlot: Value<ChildSlot<*, Child>>

    sealed interface Child {
        class Info(val linkBrowser: LinkBrowser) : Child
    }
}
