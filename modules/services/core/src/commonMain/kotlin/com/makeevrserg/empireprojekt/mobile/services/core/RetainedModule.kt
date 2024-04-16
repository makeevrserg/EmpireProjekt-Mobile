package com.makeevrserg.empireprojekt.mobile.services.core

import com.arkivanov.essenty.instancekeeper.InstanceKeeper

/**
 * [RetainedModule] will retain its instance
 */
interface RetainedModule : InstanceKeeper.Instance {
    override fun onDestroy() = Unit
}
