package com.makeevrserg.empireprojekt.mobile.wear.features.ping.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.NetworkWifi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation.PingComponent

@Composable
fun PingScreen(pingComponent: PingComponent) {
    val model by pingComponent.model.collectAsState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
        ) {
            item {
                PingScreenContent(model = model)
            }
        }
    }
}

@Composable
private fun PingScreenContent(model: PingComponent.Model) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (model) {
                is PingComponent.Model.NoConnection -> {
                    Icon(
                        imageVector = Icons.Default.NetworkCheck,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                    Text(
                        text = model.updatedAt,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                is PingComponent.Model.Success -> {
                    Icon(
                        imageVector = Icons.Default.NetworkWifi,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                    Text(
                        text = model.updatedAt,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingScreenContentSuccessPreview() {
    AdaptThemeFade {
        PingScreenContent(
            model = PingComponent.Model.Success(updatedAt = "20:44:00")
        )
    }
}

@Composable
@Preview(device = "id:wearos_large_round")
private fun PingScreenContentNoConnectionPreview() {
    AdaptThemeFade {
        PingScreenContent(
            model = PingComponent.Model.NoConnection(updatedAt = "20:44:00")
        )
    }
}
