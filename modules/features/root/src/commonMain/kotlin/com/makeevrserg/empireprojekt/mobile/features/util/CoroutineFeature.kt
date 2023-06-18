package com.makeevrserg.empireprojekt.mobile.features.util

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * This feature allows us to use [CoroutineScope] inside Components
 */
interface CoroutineFeature : InstanceKeeper.Instance, CoroutineScope {
    override val coroutineContext: CoroutineContext
    override fun onDestroy() {
        cancel()
    }

    class Default : CoroutineFeature {
        override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    }
}
