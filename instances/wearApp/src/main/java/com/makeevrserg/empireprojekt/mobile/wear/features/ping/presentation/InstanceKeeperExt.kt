package com.makeevrserg.empireprojekt.mobile.wear.features.ping.presentation

import com.arkivanov.essenty.instancekeeper.InstanceKeeper

object InstanceKeeperExt {
    class InstanceKeeperWrapper<T>(
        val instance: T,
        private val onDestroy: T.() -> Unit
    ) : InstanceKeeper.Instance {
        override fun onDestroy() {
            onDestroy.invoke(instance)
            super.onDestroy()
        }
    }

    fun <T> T.wrapInstanceKeeper(onDestroy: T.() -> Unit) = InstanceKeeperWrapper(
        instance = this,
        onDestroy = onDestroy
    )
}
