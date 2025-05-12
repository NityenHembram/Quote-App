package com.ndroid.quotesapp.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.mapper.toDomain
import com.ndroid.quotesapp.repo.NetworkRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


/**
 * Created by Nityen on 08-05-2025.
 */

const val ONE_TIME_REQUEST = "One time Request"

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workParam: WorkerParameters,
    private val dao: DatabaseDao,
    private val networkRepo: NetworkRepo
) : CoroutineWorker(context, workParam) {
    override suspend fun doWork(): Result {
        Log.d("catching", "Fetch Worker: $")
        try {
            networkRepo.getQuoteFromApi().collect {
                Log.d("catching", "fatched data: $it")
                dao.insertQuote(it.toDomain(ONE_TIME_REQUEST))
            }
            return Result.success()
        } catch (e: Exception) {
            Log.d("catching", "Error Fetchworker Worker: $e")
            return Result.failure()
        }


    }
}