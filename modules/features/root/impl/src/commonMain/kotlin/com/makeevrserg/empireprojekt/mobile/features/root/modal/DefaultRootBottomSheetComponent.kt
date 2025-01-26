package com.makeevrserg.empireprojekt.mobile.features.root.modal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import kotlinx.serialization.Serializable

class DefaultRootBottomSheetComponent(
    componentContext: ComponentContext,
    coreModule: CoreModule
) : RootBottomSheetComponent, ComponentContext by componentContext {
    private val slotNavigation = SlotNavigation<Configuration>()

    override val childSlot: Value<ChildSlot<Configuration, RootBottomSheetComponent.Child>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
        serializer = Configuration.serializer(),
        childFactory = { configuration, childContext ->
            when (configuration) {
                Configuration.Info -> {
                    RootBottomSheetComponent.Child.Info(coreModule.linkBrowser)
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

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Info : Configuration
    }
}
