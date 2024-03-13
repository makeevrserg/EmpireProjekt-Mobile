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
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule

class DefaultRootBottomSheetComponent(
    componentContext: ComponentContext,
    coreModule: CoreModule
) : RootBottomSheetComponent, ComponentContext by componentContext {
    private val slotNavigation = SlotNavigation<Configuration>()

    override val childSlot: Value<ChildSlot<Configuration, RootBottomSheetComponent.Child>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
        childFactory = { configuration, childContext ->
            when (configuration) {
                Configuration.Info -> {
                    RootBottomSheetComponent.Child.Info(coreModule.linkBrowser)
                }
            }
        }
    )

    private fun activateConfiguration(
        configuration: Configuration,
        isSameConfiguration: (configuration: Configuration?) -> Boolean
    ) {
        val currentChild = childSlot.value.child
        when {
            isSameConfiguration.invoke(currentChild?.configuration) -> dismiss()
            currentChild == null -> slotNavigation.activate(configuration)
            else -> dismiss()
        }
    }

    override fun dismiss() {
        slotNavigation.dismiss()
    }

    override fun showInfoSheet() {
        activateConfiguration(
            configuration = Configuration.Info,
            isSameConfiguration = { configuration ->
                configuration is Configuration.Info
            }
        )
    }

    sealed interface Configuration : Parcelable {
        @Parcelize
        data object Info : Configuration
    }
}
