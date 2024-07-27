package com.makeevrserg.empireprojekt.mobile.features.towny.towns.di

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.empireprojekt.mobile.features.towny.towns.presentation.TownsComponent

interface TownsModule {
    fun createTownsComponent(componentContext: ComponentContext): TownsComponent
}
