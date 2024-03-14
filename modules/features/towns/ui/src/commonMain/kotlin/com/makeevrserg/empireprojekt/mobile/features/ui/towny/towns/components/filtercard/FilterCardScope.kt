package com.makeevrserg.empireprojekt.mobile.features.ui.towny.towns.components.filtercard

import androidx.compose.foundation.layout.ColumnScope

interface FilterCardScope : ColumnScope {
    class Default(columnScope: ColumnScope) : FilterCardScope, ColumnScope by columnScope
}
