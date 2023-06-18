package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.LocalAppTheme

@Composable
fun ComposeApplication(content: @Composable () -> Unit) {
    val appTheme = AppTheme.DefaultDarkTheme

    CompositionLocalProvider(
        LocalAppTheme provides appTheme,
        content = {
            MaterialTheme(
                colors = LocalAppTheme.current.materialColor,
                typography = LocalAppTheme.current.typography,
                shapes = LocalAppTheme.current.shapes,
                content = content
            )
        }
    )
}
