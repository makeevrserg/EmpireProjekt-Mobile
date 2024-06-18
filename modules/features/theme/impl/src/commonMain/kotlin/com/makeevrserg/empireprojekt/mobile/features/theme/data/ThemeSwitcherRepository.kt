package com.makeevrserg.empireprojekt.mobile.features.theme.data

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate

internal interface ThemeSwitcherRepository {

    val themeFlowStorageValue: StateFlowMutableKrate<Theme>
}
