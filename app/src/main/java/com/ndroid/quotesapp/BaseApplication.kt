package com.ndroid.quotesapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.ndroid.quotesapp.worker.notificationChannelId
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


/**
 * Created by Nityen on 08-05-2025.
 */

const val name = "someName"

@HiltAndroidApp
class BaseApplication: Application() {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(
            this, Configuration.Builder().setWorkerFactory(hiltWorkerFactory).build()
        )
        val notificationChannel = NotificationChannel(notificationChannelId, name,NotificationManager.IMPORTANCE_HIGH)
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}