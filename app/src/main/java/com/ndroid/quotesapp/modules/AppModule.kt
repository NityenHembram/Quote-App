package com.ndroid.quotesapp.modules

import android.content.Context
import androidx.work.WorkManager
import com.ndroid.quotesapp.database.DatabaseDao
import com.ndroid.quotesapp.repo.DatabaseRepo
import com.ndroid.quotesapp.repo.DatabaseRepoImpl
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
class AppModule {

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context):WorkManager{
       return WorkManager.getInstance(context)
    }


    @Provides
    fun provideDatabaseRepo(workManager: WorkManager, dao: DatabaseDao):DatabaseRepo{
        return DatabaseRepoImpl(workManager, dao)
    }
}