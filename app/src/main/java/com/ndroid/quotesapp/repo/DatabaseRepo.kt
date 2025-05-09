package com.ndroid.quotesapp.repo

import com.ndroid.quotesapp.models.QuoteModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nityen on 08-05-2025.
 */


interface DatabaseRepo {
   suspend fun insertQuote(quoteModel: QuoteModel)
   suspend fun getAllQuote(): Flow<List<QuoteModel>>
   suspend fun setupPeriodWorkRequest()
}