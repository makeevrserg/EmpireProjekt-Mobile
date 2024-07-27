package com.makeevrserg.empireprojekt.mobile.features.root.pager.data

import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.astrainteractive.klibs.kstorage.api.StateFlowKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultStateFlowMutableKrate

interface LastBottomItemRepository {
    val lastBottomItemIndex: StateFlowKrate.Mutable<PagerBottomBarItem>
}

class LastBottomItemRepositoryImpl(private val settings: Settings) : LastBottomItemRepository {
    @Suppress("VariableNaming")
    private val KEY = "LAST_BOTTOM"

    override val lastBottomItemIndex = DefaultStateFlowMutableKrate(
        factory = { PagerBottomBarItem.Status },
        saver = { settings[KEY] = it.ordinal },
        loader = {
            PagerBottomBarItem.entries.getOrElse(
                index = settings[KEY] ?: 0,
                defaultValue = { PagerBottomBarItem.Status }
            )
        }
    )
}
