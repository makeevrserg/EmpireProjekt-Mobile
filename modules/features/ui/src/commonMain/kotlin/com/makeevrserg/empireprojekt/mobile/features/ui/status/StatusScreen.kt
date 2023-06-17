package com.makeevrserg.empireprojekt.mobile.features.ui.status

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.features.status.EmpireProjektStatusComponent

@Composable
fun StatusScreen() {
    Scaffold(
        modifier = Modifier
    ) {
        StatusWidget(EmpireProjektStatusComponent())
    }
}
