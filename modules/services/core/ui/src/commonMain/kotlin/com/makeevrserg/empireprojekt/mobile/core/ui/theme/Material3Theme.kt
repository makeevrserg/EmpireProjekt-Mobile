package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

sealed class Material3Theme(
    val colorScheme: ColorScheme,
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes(),
) {

    data object DefaultDark : Material3Theme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF252B30),
            primaryContainer = Color(0xFF161A1D),
            background = Color(0xFF161A1D),
            secondary = Color(0xFFFFC100),
            secondaryContainer = Color(0xFF1B76CA),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF697C8A),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000),
        ),
    )

    data object DefaultLight : Material3Theme(
        colorScheme = lightColorScheme(
            primary = Color(0xFFFFFFFF),
            primaryContainer = Color(0xFFF1F1F1),
            background = Color(0xFFF1F1F1),
            secondary = Color(0xFFFFC100),
            secondaryContainer = Color(0xFF106BBB),
            onPrimary = Color(0xFF181818),
            onSecondary = Color(0xFF4E5C66),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000),
        ),
    )
}
