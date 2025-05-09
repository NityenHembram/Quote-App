package com.ndroid.quotesapp.modules

import android.content.Context
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.database.DatabaseInit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Nityen on 08-05-2025.
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
   fun provideDatabaseInit(@ApplicationContext context: Context):DatabaseInit = DatabaseInit.getInstance(context)


    @Provides
    fun provideDao(databaseInit: DatabaseInit):DatabaseDao = databaseInit.getDatabaseDao()
}