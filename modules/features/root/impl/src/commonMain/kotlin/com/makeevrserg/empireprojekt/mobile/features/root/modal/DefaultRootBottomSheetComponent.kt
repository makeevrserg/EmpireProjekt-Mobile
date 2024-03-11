package com.makeevrserg.empireprojekt.mobile.features.root.modal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule

class DefaultRootBottomSheetComponent(
    componentContext: ComponentContext,
    servicesModule: ServicesModule
) : RootBottomSheetComponent, ComponentContext by componentContext {
    private val slotNavigation = SlotNavigation<Configuration>()

    override val childSlot: Value<ChildSlot<*, RootBottomSheetComponent.Child>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
        childFactory = { configuration, context ->
            when (configuration) {
                Configuration.Info -> {
                    RootBottomSheetComponent.Child.Info(servicesModule.linkBrowser.value)
                }
            }
        }
    )

    override fun dismiss() {
        slotNavigation.dismiss()
    }

    override fun showInfoSheet() {
        slotNavigation.activate(Configuration.Info)
    }

    sealed interface Configuration : Parcelable {
        @Parcelize
        data object Info : Configuration
    }
}
