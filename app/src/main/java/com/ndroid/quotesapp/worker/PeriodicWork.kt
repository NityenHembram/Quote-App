package com.ndroid.quotesapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.mapper.toDomain
import com.ndroid.quotesapp.repo.NetworkRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


/**
 * Created by Nityen on 09-05-2025.
 */



const val PERIODIC_REQUEST = "One time Request"

@HiltWorker
class PeriodicWork @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workParam: WorkerParameters,
    private val dao: DatabaseDao,
    private val networkRepo: NetworkRepo
) : CoroutineWorker(context, workParam) {
    override suspend fun doWork(): Result {
        try {
            networkRepo.getQuoteFromApi().collect {
                dao.insertQuote(it.toDomain(PERIODIC_REQUEST))
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }


    }
}