package com.makeevrserg.empireprojekt.mobile.service.controller

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.makeevrserg.empireprojekt.mobile.MainActivity
import com.makeevrserg.empireprojekt.mobile.service.StatusService

class StatusServiceControllerImpl(activity: MainActivity) : StatusServiceController {
    private var activity: MainActivity? = activity

    private val context: Context
        get() = activity?.applicationContext ?: error("Activity already disposed")

    private val requestPermissionLauncher = activity.registerForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        callback = { isGranted: Boolean ->
            if (isGranted) launchStatusService()
        }
    )

    override fun hasNotificationPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationPermission = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            )
            return notificationPermission == PackageManager.PERMISSION_GRANTED
        } else {
            return true
        }
    }

    override fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    override fun launchStatusService() {
        if (!hasNotificationPermission()) {
            askNotificationPermission()
            return
        }
        Intent(context, StatusService::class.java).let { service ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(service)
            } else {
                context.startService(service)
            }
        }
    }

    override fun close() {
        requestPermissionLauncher.unregister()
        activity = null
    }
}
