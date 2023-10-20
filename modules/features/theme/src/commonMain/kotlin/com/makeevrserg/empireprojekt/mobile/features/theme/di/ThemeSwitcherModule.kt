package com.makeevrserg.empireprojekt.mobile.features.theme.di

import com.makeevrserg.empireprojekt.mobile.features.theme.data.ThemeSwitcherRepository
import com.makeevrserg.empireprojekt.mobile.features.theme.data.ThemeSwitcherRepositoryImpl
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kdi.Single
import ru.astrainteractive.klibs.kdi.getValue

interface ThemeSwitcherModule {

    val themeSwitcherRepository: ThemeSwitcherRepository

    class Default(settings: Settings) : ThemeSwitcherModule {
        override val themeSwitcherRepository: ThemeSwitcherRepository by Single {
            ThemeSwitcherRepositoryImpl(settings)
        }
    }
}
