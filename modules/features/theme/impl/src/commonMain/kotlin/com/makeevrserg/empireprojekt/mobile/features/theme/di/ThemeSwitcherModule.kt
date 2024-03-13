package com.makeevrserg.empireprojekt.mobile.features.theme.di

import com.makeevrserg.empireprojekt.mobile.features.theme.data.ThemeSwitcherRepositoryImpl
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.DefaultThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.presentation.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.services.core.di.CoreModule
import ru.astrainteractive.klibs.kdi.getValue

interface ThemeSwitcherModule {

    val themeSwitcherComponent: ThemeSwitcherComponent

    class Default(coreModule: CoreModule) : ThemeSwitcherModule {
        override val themeSwitcherComponent: ThemeSwitcherComponent by lazy {
            DefaultThemeSwitcherComponent(
                themeSwitcherRepository = ThemeSwitcherRepositoryImpl(
                    settings = coreModule.settings
                )
            )
        }
    }
}
