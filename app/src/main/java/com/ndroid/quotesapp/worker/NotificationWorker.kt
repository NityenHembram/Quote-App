package com.ndroid.quotesapp.worker

import android.Manifest
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.ndroid.quotesapp.R
import com.ndroid.quotesapp.models.QuoteDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

const val QUOTE = "QUOTE"
const val notificationChannelId = "notificadtionChannelid"

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val quoteJson = workerParams.inputData.getString(QUOTE)
            val quote = Gson().fromJson<QuoteDto>(quoteJson, QuoteDto::class.java)
            val notification = NotificationCompat.Builder(context, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Quote's")
                .setContentText(quote.quote.plus(quote.author))
                .build()

            NotificationManagerCompat.from(context).notify(1, notification)

        }

        return Result.success()
    }
}