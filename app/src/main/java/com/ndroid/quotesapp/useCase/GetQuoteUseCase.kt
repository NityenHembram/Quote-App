package com.ndroid.quotesapp.useCase

import com.ndroid.quotesapp.repo.DatabaseRepo
import javax.inject.Inject


/**
 * Created by Nityen on 09-05-2025.
 */


class GetQuoteUseCase @Inject constructor(private val repo:DatabaseRepo) {
    operator fun invoke() = repo.
}