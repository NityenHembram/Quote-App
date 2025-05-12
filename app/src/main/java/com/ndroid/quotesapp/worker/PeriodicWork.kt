package com.ndroid.quotesapp.worker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ndroid.quotesapp.R
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.mapper.toDomain
import com.ndroid.quotesapp.repo.NetworkRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


/**
 * Created by Nityen on 09-05-2025.
 */


const val PERIODIC_REQUEST = "Periodic Request"

@HiltWorker
class PeriodicWork @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workParam: WorkerParameters,
    private val dao: DatabaseDao,
    private val networkRepo: NetworkRepo
) : CoroutineWorker(context, workParam) {
    override suspend fun doWork(): Result {
        try {
            Log.d("catching", "periodic work: $")
            networkRepo.getQuoteFromApi().collect { response ->
                dao.insertQuote(response.toDomain(PERIODIC_REQUEST))

                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    val notification = NotificationCompat.Builder(context, notificationChannelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Quote's")
                        .setContentText(response.quote.plus(response.author)).build()

                    NotificationManagerCompat.from(context).notify(1, notification)
                }

            }

            return Result.success()
        } catch (e: Exception) {
            Log.d("catching", "error periodic work: $e")
            return Result.failure()
        }
    }
}