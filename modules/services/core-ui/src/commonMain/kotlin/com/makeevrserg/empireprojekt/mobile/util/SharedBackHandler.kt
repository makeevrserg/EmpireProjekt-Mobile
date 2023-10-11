package com.makeevrserg.empireprojekt.mobile.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.backhandler.BackHandlerOwner

@Composable
fun SharedBackHandler(owner: BackHandlerOwner, onBack: () -> Unit) {
    val backCallback = BackCallback(true, onBack)
    DisposableEffect(owner) {
        owner.backHandler.register(backCallback)
        onDispose {
            owner.backHandler.unregister(backCallback)
        }
    }
}
