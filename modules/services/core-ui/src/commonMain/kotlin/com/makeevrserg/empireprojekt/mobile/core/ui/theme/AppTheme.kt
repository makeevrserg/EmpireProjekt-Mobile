@file:Suppress("MagicNumber", "ConstructorParameterNaming")

package com.makeevrserg.empireprojekt.mobile.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class AppTheme(
    val materialColor: Colors,
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes(),
    val dimens: Dimens = Dimens(),
    val textSizes: TextSizes = TextSizes(),
    val alColors: ALColors = ALColors(),
    val isDark: Boolean
) {
    companion object {
        val materialColor: Colors
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.materialColor
        val alColors: ALColors
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.alColors
        val dimens: Dimens
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.dimens
        val typography: Typography
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.typography
        val textSizes: TextSizes
            @Composable
            @ReadOnlyComposable
            get() = LocalAppTheme.current.textSizes
    }

    class ALColors(
        val colorNegative: Color = Color(0xFFB00020),
        val colorPositive: Color = Color(0xFF26B000),
        val astraRed: Color = Color(0xFFbc2551),
        val astraBlue: Color = Color(0xFF304d7b),
        val astraOrange: Color = Color(0xFFd34829),
        val astraYellow: Color = Color(0xFFd28429),
        val onSecondaryVariant: Color = Color(0xFFFFFFFF)
    )

    class Dimens(
        val XXS: Dp = 4.dp,
        val XS: Dp = 8.dp,
        val S: Dp = 16.dp,
        val M: Dp = 21.dp,
        val L: Dp = 32.dp,
        val SXL: Dp = 40.dp,
        val XL: Dp = 48.dp,
        val XXL: Dp = 64.dp,
    )

    class TextSizes(
        val XXS: TextUnit = 4.sp,
        val XS: TextUnit = 8.sp,
        val S: TextUnit = 12.sp,
        val M: TextUnit = 16.sp,
        val L: TextUnit = 18.sp,
        val XL: TextUnit = 20.sp,
        val XXL: TextUnit = 24.sp,
    )

    object DefaultDarkTheme : AppTheme(
        materialColor = darkColors(
            primary = Color(0xFF252B30),
            primaryVariant = Color(0xFF161A1D),
            background = Color(0xFF161A1D),
            secondary = Color(0xFFFFC100),
            secondaryVariant = Color(0xFF1B76CA),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF697C8A),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000),
        ),
        isDark = true
    )

    object DefaultLightTheme : AppTheme(
        materialColor = lightColors(
            primary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFFF1F1F1),
            background = Color(0xFFF1F1F1),
            onSecondary = Color(0xFF4E5C66),
            onPrimary = Color(0xFF181818),
            secondary = Color(0xFF214AB3),
            secondaryVariant = Color(0xFF106BBB),
            onSurface = Color(0xFF000000),
        ),
        isDark = false
    )
}

@Suppress("ComposableNaming", "CompositionLocalAllowlist")
val LocalAppTheme: ProvidableCompositionLocal<AppTheme> = compositionLocalOf {
    AppTheme.DefaultDarkTheme
}
