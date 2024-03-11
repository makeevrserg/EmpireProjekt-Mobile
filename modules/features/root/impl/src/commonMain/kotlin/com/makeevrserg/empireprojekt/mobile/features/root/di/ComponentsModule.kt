package com.makeevrserg.empireprojekt.mobile.features.root.di

import com.makeevrserg.empireprojekt.mobile.features.status.root.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import ru.astrainteractive.klibs.kdi.Single

interface ComponentsModule {
    // Global components
    val rootStatusComponent: Single<RootStatusComponent>
    val themeSwitcherComponent: Single<ThemeSwitcherComponent>
}
