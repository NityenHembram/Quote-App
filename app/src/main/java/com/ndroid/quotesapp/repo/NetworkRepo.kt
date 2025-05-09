package com.ndroid.quotesapp.repo

import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.models.QuoteDto
import com.ndroid.quotesapp.models.QuoteModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nityen on 08-05-2025.
 */


interface NetworkRepo {
    suspend  fun getQuoteFromApi():Flow<QuoteDto>
}