package com.ndroid.quotesapp.useCase

import com.ndroid.quotesapp.repo.DatabaseRepo
import javax.inject.Inject

class SetupPeriodicWorkRequestUseCase @Inject constructor(private val repo: DatabaseRepo){
    suspend operator fun invoke() = repo.setupPeriodWorkRequest()
}