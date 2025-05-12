package com.ndroid.quotesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ndroid.quotesapp.models.QuoteModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nityen on 08-05-2025.
 */


@Dao
interface DatabaseDao {

    @Insert
    suspend fun insertQuote(quoteModel: QuoteModel)

    @Query("SELECT * FROM QuoteModel Order by time DESC")
    fun getAllQuote():Flow<List<QuoteModel>>

}