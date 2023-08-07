package com.makeevrserg.empireprojekt.mobile.features.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.features.root.di.ServicesModule
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

class DefaultRootBottomSheetComponent(
    componentContext: ComponentContext,
    servicesModule: ServicesModule
) : RootBottomSheetComponent, ComponentContext by componentContext {
    private val slotNavigation = SlotNavigation<RootBottomSheetComponent.Child>()

    override val childSlot: Value<ChildSlot<*, Configuration>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
        childFactory = { configuration, context ->
            when (configuration) {
                RootBottomSheetComponent.Child.Settings -> {
                    Configuration.SettingsChild(servicesModule.linkBrowser.value)
                }
            }
        }
    )

    override fun dismissSlotChild() {
        slotNavigation.dismiss()
    }

    override fun pushSlot(slot: RootBottomSheetComponent.Child) {
        slotNavigation.activate(slot)
    }

    sealed interface Configuration {
        class SettingsChild(val linkBrowser: LinkBrowser) : Configuration
    }
}
