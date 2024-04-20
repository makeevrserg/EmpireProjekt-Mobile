package com.makeevrserg.empireprojekt.mobile.service.controller

interface StatusServiceController {
    fun hasNotificationPermission(): Boolean

    fun askNotificationPermission()

    fun launchStatusService()

    fun close()
}
