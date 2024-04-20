package com.makeevrserg.empireprojekt.mobile.service

import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.makeevrserg.empireprojekt.mobile.wear.messenger.ping.util.PingWearMessage
import com.makeevrserg.empireprojekt.mobile.work.CheckStatusWork
import com.makeevrserg.empireprojekt.mobile.work.PingWorker
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class StatusService : LifecycleService() {

    private fun <T : ListenableWorker> startWork(clazz: Class<T>) {
        val request = OneTimeWorkRequest.Builder(clazz).build()
        val instanceWorkManager = WorkManager.getInstance(applicationContext)
        instanceWorkManager.enqueue(request)
    }

    private val dispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    private fun initPingWork() {
        lifecycleScope.launch(dispatcher) {
            while (isActive) {
                delay(PingWearMessage.DELAY)
                startWork(PingWorker::class.java)
            }
        }
    }

    private fun initStatusWork() {
        lifecycleScope.launch(dispatcher) {
            while (isActive) {
                delay(CheckStatusWork.DELAY)
                startWork(CheckStatusWork::class.java)
            }
        }
    }

    private fun startNotification() {
        val notification = StatusNotificationFactory(applicationContext).create()
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(
                StatusNotificationFactory.SERVICE_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(StatusNotificationFactory.SERVICE_ID, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand: ")
        if (intent?.action == StatusNotificationFactory.ACTION_STOP) {
            stopSelfInternal()
        }
        return START_STICKY
    }

    private fun stopSelfInternal() = lifecycleScope.launch {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        }
        stopSelf()
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: ")
        startNotification()
        initStatusWork()
        initPingWork()
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        lifecycleScope.cancel()
        super.onDestroy()
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(TAG, "stopService: ")
        return super.stopService(name)
    }

    companion object {
        private const val TAG = "StatusService"
    }
}
