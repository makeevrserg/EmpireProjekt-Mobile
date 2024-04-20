package com.makeevrserg.empireprojekt.mobile.service

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.makeevrserg.empireprojekt.mobile.MainActivity
import com.makeevrserg.empireprojekt.mobile.core.resources.R

class StatusNotificationFactory(private val applicationContext: Context) {
    private val intentOpen by lazy {
        PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
    private val intentStop by lazy {
        PendingIntent.getService(
            applicationContext,
            0,
            Intent(applicationContext, StatusService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotificationChannel() {
        NotificationManagerCompat.from(applicationContext).createNotificationChannel(
            NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_MIN)
                .setName("Connection status")
                .build()
        )
    }

    fun create(): Notification {
        createNotificationChannel()

        // Make sure we are foreground.
        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Empire Wear")
            .setContentText("Wear sync is running")
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setSmallIcon(R.drawable.ic_splash)
            .setColor(Color.TRANSPARENT)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.ic_splash
                )
            )
            .setContentIntent(intentOpen)
            .setDeleteIntent(intentStop)
            .addAction(R.drawable.ic_splash, "Stop", intentStop)
            .setOngoing(true)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
            .setLocalOnly(true)
            .setShowWhen(false)
            .build()
    }

    companion object {
        const val CHANNEL_ID = "connection"
        const val SERVICE_ID = 100
        const val ACTION_STOP = "com.makeevrserg.empireprojekt.mobile.work.StatusService.STOP"
    }
}
