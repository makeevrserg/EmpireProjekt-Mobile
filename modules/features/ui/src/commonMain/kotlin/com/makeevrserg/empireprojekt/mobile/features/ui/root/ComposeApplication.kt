package com.makeevrserg.empireprojekt.mobile.features.ui.root

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.LocalAppTheme
import com.makeevrserg.empireprojekt.mobile.features.root.DefaultRootComponent

@Composable
fun ComposeApplication(
    component: DefaultRootComponent,
    modifier: Modifier = Modifier
) {
    val appTheme = AppTheme.DefaultDarkTheme

    CompositionLocalProvider(
        LocalAppTheme provides appTheme,
        content = {
            MaterialTheme(
                colors = LocalAppTheme.current.materialColor,
                typography = LocalAppTheme.current.typography,
                shapes = LocalAppTheme.current.shapes,
                content = {
                    ApplicationContent(
                        component = component,
                        modifier = modifier
                    )
                }
            )
        }
    )
}
