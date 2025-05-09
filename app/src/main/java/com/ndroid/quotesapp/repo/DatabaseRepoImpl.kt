package com.ndroid.quotesapp.repo

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.models.QuoteModel
import com.ndroid.quotesapp.worker.FetchWorker
import com.ndroid.quotesapp.worker.PeriodicWork
import kotlinx.coroutines.flow.Flow
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Nityen on 08-05-2025.
 */


class DatabaseRepoImpl @Inject constructor(
    private val workManager: WorkManager,
    private val dao: DatabaseDao
) : DatabaseRepo {

    //    Here we are starting workmanager which is handleing the network request and saving into the database
    override suspend fun insertQuote(quoteModel: QuoteModel) {
        val constrain = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constrain)
            .build()
        workManager.enqueue(workRequest)

    }

    override suspend fun getAllQuote(): Flow<List<QuoteModel>> {
        return dao.getAllQuote()

    }

    override suspend fun setupPeriodWorkRequest() {
        val constrain = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequestBuilder<PeriodicWork>(15, TimeUnit.MINUTES)
            .setConstraints(constrain).build()
        workManager.enqueueUniquePeriodicWork(
            "anything",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }


}