package com.ndroid.quotesapp.repo

import android.util.Log
import com.ndroid.quotesapp.models.QuoteDto
import com.ndroid.quotesapp.models.QuoteModel
import com.ndroid.quotesapp.networks.ApiInterface
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Created by Nityen on 08-05-2025.
 */


class NetworkRepoImpl @Inject constructor(private val client: HttpClient):NetworkRepo {
    override suspend fun getQuoteFromApi(): Flow<QuoteDto> = flow {
        val quote = client.get {
            url {
                path(ApiInterface.RANDOM_QUOTES)
            }
        }.body<QuoteDto>()
        emit(quote)
    }
}