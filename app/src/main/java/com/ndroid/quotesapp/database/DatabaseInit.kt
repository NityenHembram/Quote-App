package com.ndroid.quotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ndroid.quotesapp.models.QuoteModel


/**
 * Created by Nityen on 08-05-2025.
 */

@Database(entities = [QuoteModel::class], version = 1, exportSchema = false)
abstract class DatabaseInit: RoomDatabase() {

    companion object{
        fun getInstance(context: Context) = Room.databaseBuilder(context, DatabaseInit::class.java, "quote db").build()
    }

    abstract fun getDatabaseDao(): DatabaseDao

}